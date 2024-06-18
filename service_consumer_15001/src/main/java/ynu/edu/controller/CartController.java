package ynu.edu.controller;

import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ynu.edu.entity.CommonResult;
import ynu.edu.entity.User;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/getCartById/{userId}")
    public CommonResult<User> getCartById(@PathVariable("userId") Integer userId){

        CommonResult<User> result=restTemplate.getForObject(
                "http://provider-server/user/getUserById/"+userId.toString(),CommonResult.class);
        return result;//无需instance，加上provider-server自动获取负载的服务

    }
    // POST 方法
    @PostMapping("/createCart")
    public CommonResult<User> createCart(@RequestBody User user) {
        CommonResult<User> result = restTemplate.postForObject(
                "http://provider-server/user/createUser", user, CommonResult.class);
        return result;
    }

    // PUT 方法
    @PutMapping("/updateCart")
    public CommonResult<User> updateCart(@RequestBody User user) {
        CommonResult<User> result = restTemplate.exchange(
                "http://provider-server/user/updateUser", HttpMethod.PUT, new HttpEntity<>(user), CommonResult.class).getBody();
        return result;
    }

    // DELETE 方法
    @DeleteMapping("/deleteCart/{userId}")
    public CommonResult<Void> deleteCart(@PathVariable("userId") Integer userId) {
        CommonResult<Void> result = restTemplate.exchange(
                "http://provider-server/user/deleteUser/{id}", HttpMethod.DELETE, null, CommonResult.class, userId).getBody();
        return result;
    }
}

