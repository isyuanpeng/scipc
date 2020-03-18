package com.yuanpeng.security.controller;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.yuanpeng.aop.Log;
import com.yuanpeng.common.utils.SecurityUtils;
import com.yuanpeng.dto.RoleSmallDto;
import com.yuanpeng.dto.UserDto;
import com.yuanpeng.entity.User;
import com.yuanpeng.security.config.AnonymousAccess;
import com.yuanpeng.security.config.SecurityProperties;
import com.yuanpeng.security.config.TokenProvider;
import com.yuanpeng.security.service.OnlineUserService;
import com.yuanpeng.security.vo.AuthUser;
import com.yuanpeng.security.vo.JwtUser;
import com.yuanpeng.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-18 11:27
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "Security登录")
public class AuthController {

//    @Value("${rsa.private_key}")
//    private String privateKey;
    @Value("${single.login:false}")
    private Boolean singleLogin;

    private final SecurityProperties properties;
    private final UserDetailsService userDetailsService;
    private final OnlineUserService onlineUserService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;

    public AuthController(SecurityProperties properties, UserDetailsService userDetailsService, OnlineUserService onlineUserService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService) {
        this.properties = properties;
        this.userDetailsService = userDetailsService;
        this.onlineUserService = onlineUserService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
    }

    @Log("用户登录")
    @ApiOperation("登录授权")
    @AnonymousAccess
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUser authUser, HttpServletRequest request) {
        // 密码解密
        /*RSA rsa = new RSA(privateKey, null);
        String password = new String(rsa.decrypt(authUser.getPassword(), KeyType.PrivateKey));*/

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌
        String token = tokenProvider.createToken(authentication);
        final JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        // 保存在线信息
        onlineUserService.save(jwtUser, token, request);
        // 返回 token 与 用户信息
        Map<String,Object> authInfo = new HashMap<String,Object>(2){{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUser);
        }};
        if(singleLogin){
            //踢掉之前已经登录的token
            onlineUserService.checkLoginOnUser(authUser.getUsername(),token);
        }
        return ResponseEntity.ok(authInfo);
    }


    @PostMapping("/signin")
    @Log("注册")
    @ApiOperation("注册用户")
    @AnonymousAccess
//    @PreAuthorize("@el.check('user:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody User resources){
        resources.setPassword(new BCryptPasswordEncoder().encode(resources.getPassword()));
        return new ResponseEntity<>(userService.create(resources), HttpStatus.CREATED);
    }

    @ApiOperation("退出登录")
    @AnonymousAccess
    @DeleteMapping(value = "/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request){
        onlineUserService.logout(tokenProvider.getToken(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public ResponseEntity<Object> getUserInfo(){
        UserDto user = userService.findByUsername(SecurityUtils.getUsername());
        Set<RoleSmallDto> roleSmallDtos = user.getRoles();

        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("roles", roleSmallDtos.stream().map(RoleSmallDto::getCode).collect(Collectors.toSet()));
        return ResponseEntity.ok(result);
    }
}
