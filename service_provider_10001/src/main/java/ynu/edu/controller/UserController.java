package ynu.edu.controller;

import ynu.edu.entity.CommonResult;
import ynu.edu.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable("userId") Integer userId) {
        CommonResult<User> result = new CommonResult<>();
        Integer code = 200;
        String message = "success(11000)";
        try {
            User u = new User(userId, "小明", "123456");
            result.setResult(u);
        } catch (Exception e) {
            code = 500;
            message = "failed";
        }
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    @PostMapping("/createUser")
    public CommonResult<User> createUser(@RequestBody User user) {
        CommonResult<User> result = new CommonResult<>();
        Integer code = 200;
        String message = "Success";
        try {
            User createdUser = new User(user.getUserId(), user.getUserName(), user.getPassword());
            result.setResult(createdUser);
        } catch (Exception e) {
            code = 500;
            message = "Failed to create user";
        }
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    @PutMapping("/updateUser")
    public CommonResult<User> updateUser(@RequestBody User user) {
        CommonResult<User> result = new CommonResult<>();
        Integer code = 200;
        String message = "Success";
        try {
            User updatedUser = new User(user.getUserId(), user.getUserName(), user.getPassword());
            result.setResult(updatedUser);
        } catch (Exception e) {
            code = 500;
            message = "Failed to update user";
        }
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Integer userId) {
        try {
            // 在这里实现删除用户的逻辑
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}