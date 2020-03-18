package com.yuanpeng;

import com.yuanpeng.dto.RoleDto;
import com.yuanpeng.entity.Role;
import com.yuanpeng.service.RoleService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-16 10:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppRun.class)
public class RoleTest  {

    @Autowired
    private RoleService roleService;

    @Test
    @Ignore
    public void testRoleCreate() {
        Role role = new Role();
        role.setId(1L);
        role.setName("学生");
        role.setRemark("学生角色");
        RoleDto roleDto = roleService.create(role);
        System.out.println(roleDto);
    }

}
