package com.kq.controller;

import com.kq.pojo.User;
import com.kq.SpecialService.FastApiService;
import com.kq.service.IUserService;
import com.kq.util.JwtTokenUtil;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/validateToken")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (jwtTokenUtil.validateToken(token)) {
                return ResponseEntity.ok().build(); // Token 有效
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Token 无效
    }
    @PostMapping("/callModel")
    public String callModel(@RequestBody Map<String, Object> requestBody) {
        String prompt = (String) requestBody.get("prompt");
        Number max_length_num = (Number) requestBody.get("max_length");
        Number top_p_num = (Number) requestBody.get("top_p");
        Number temperature_num = (Number) requestBody.get("temperature");
        Integer max_length = max_length_num != null ? max_length_num.intValue() : 2048;
        Double top_p = top_p_num != null ? top_p_num.doubleValue() : 1.0;
        Double temperature = temperature_num != null ? temperature_num.doubleValue() : 0.7;
        String userId = (String) requestBody.get("userId");
        System.out.println(prompt);
        return fastApiService.callModel(prompt, max_length, top_p, temperature,userId);
    }
    @PostMapping("/clearHistory")
    public ResponseEntity<?> clearHistory(@RequestBody Map<String, Object> requestBody) {
        String userId = (String) requestBody.get("userId");
        if (userId == null || userId.isEmpty()) {
            return ResponseEntity.badRequest().body("用户ID不能为空");
        }

        fastApiService.clearHistory(userId);
        return ResponseEntity.ok().build();
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
