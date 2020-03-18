package com.yuanpeng;

import com.yuanpeng.dto.PermissionDto;
import com.yuanpeng.entity.Permission;
import com.yuanpeng.service.PermissionService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-18 11:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppRun.class)
public class PermissionTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    @Ignore
    public void testPermissionCreate() {
        Permission permission = new Permission();
        permission.setName("在线用户页面");
        permission.setExpression("online");
        permission.setParentId(0L);
        permission.setRemark("在线用户");
        permission.setUrl("/online");
        permission.setType(1);
        PermissionDto permissionDto = permissionService.create(permission);
        System.out.println(permissionDto);
    }
}
