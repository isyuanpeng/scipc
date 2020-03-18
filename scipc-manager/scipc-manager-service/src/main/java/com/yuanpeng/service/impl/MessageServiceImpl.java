package com.yuanpeng.service.impl;

import com.yuanpeng.common.utils.*;
import com.yuanpeng.dto.MessageDto;
import com.yuanpeng.dto.UserSmallDto;
import com.yuanpeng.entity.Message;
import com.yuanpeng.entity.Role;
import com.yuanpeng.entity.User;
import com.yuanpeng.querycriteria.MessageQueryCriteria;
import com.yuanpeng.repository.MessageRepository;
import com.yuanpeng.repository.RoleRepository;
import com.yuanpeng.repository.UserRepository;
import com.yuanpeng.service.MessageService;
import com.yuanpeng.service.mapper.MessageMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    private final MessageMapper messageMapper;

    private final RoleRepository roleRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, MessageMapper messageMapper, RoleRepository roleRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageMapper = messageMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(MessageQueryCriteria criteria, Pageable pageable){
        Page<Message> page = messageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(messageMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<MessageDto> queryAll(MessageQueryCriteria criteria){
        return messageMapper.toDto(messageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public MessageDto findById(Long id) {
        Message message = messageRepository.findById(id).orElseGet(Message::new);
        ValidationUtil.isNull(message.getId(),"Permission","id",id);
        return messageMapper.toDto(message);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public MessageDto create(Message resources) {
        return messageMapper.toDto(messageRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Message resources) {
        Message message = messageRepository.findById(resources.getId()).orElseGet(Message::new);
        ValidationUtil.isNull( message.getId(),"Message","id",resources.getId());
        message.copy(resources);
        messageRepository.save(message);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            messageRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<MessageDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (MessageDto message : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("id", message.getId());
            map.put("发送者", message.getSender().getName());
            map.put("接受者", message.getReciver().getName());
            map.put("内容", message.getContent());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void pushToStudent(Message resources) {
        User currentUser = userRepository.findByUsername(SecurityUtils.getUsername());
        Set<User> students = roleRepository.findByCode("student").get().getUsers();
        Message root = new Message();
        root.setSender(currentUser);
        root.setReciver(currentUser);
        root.setType(1);
        root.setContent(resources.getContent());
        messageRepository.save(root);
        for (User student:
             students) {
            Message message = new Message();
            message.setSender(currentUser);
            message.setReciver(student);
            message.setContent(resources.getContent());
            message.setType(1);
            message.setIsRead(0);
            messageRepository.save(message);
        }
    }

    @Override
    public MessageDto pull() {
        User currentUser = userRepository.findByUsername(SecurityUtils.getUsername());
        return messageMapper.toDto(messageRepository.pull(currentUser.getId()));
    }
}
