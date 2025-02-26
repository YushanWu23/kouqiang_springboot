package com.kq.controller;

import com.kq.pojo.User;
import com.kq.SpecialService.FastApiService;
import com.kq.service.IUserService;
import com.kq.util.JwtTokenUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {//登录注册忘记密码，查看个人信息（头像上传）
    @Resource
    private IUserService iUserService;
    @Resource
    private FastApiService fastApiService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/callModel")
    public String callModel(@RequestBody Map<String, Object> requestBody) {
        String prompt = (String) requestBody.get("prompt");
        Integer max_length = (Integer) requestBody.get("max_length");
        Double top_p = (Double) requestBody.get("top_p");
        Double temperature = (Double) requestBody.get("temperature");
        System.out.println(prompt);
        return fastApiService.callModel(prompt, max_length, top_p, temperature);
    }

    @PostMapping("/login")
    String login(@RequestParam String userId, @RequestParam String pwd){
        return iUserService.login(userId,pwd)!=null? jwtTokenUtil.generateToken(userId) :null;
    }
    @PostMapping("/register")
    int register(@RequestParam String userId, @RequestParam String password, @RequestParam String userName, @RequestParam int userSex,@RequestParam String emailCode){
        return iUserService.register(userId,password,userName,userSex,emailCode);
    }
    @PostMapping("/sendRegisterEmailCode")
    int sendRegisterEmailCode(@RequestParam String userId){
        return iUserService.sendRegisterEmailCode(userId);
    }
    @GetMapping("/getUserInfo")//得到用户信息
    User getUserInfo(@RequestParam String userId){
        return iUserService.getUserInfo(userId);
    }

    @PostMapping("/passwordForget")     //忘记密码和修改密码共用
    int passwordForget(@RequestParam String userId, @RequestParam String newPassword,@RequestParam String emailCode){
        return iUserService.passwordForget(userId,newPassword,emailCode);
    }
    @PostMapping("/sendForgetPasswordEmailCode")
    int sendForgetPasswordEmailCode(@RequestParam String userId){
        return iUserService.sendForgetPasswordEmailCode(userId);
    }

    @PostMapping("/updateUserInfo")//更改用户信息
    int updateUserInfo (@RequestParam String userId,@RequestParam String userName,@RequestParam int userSex){
        return iUserService.updateUserInfo (userId,userName,userSex);
    }
}
