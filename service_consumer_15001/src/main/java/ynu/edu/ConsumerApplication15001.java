package ynu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ynu.edu.rule.CustomLoadBalancerConfig;


@SpringBootApplication
@LoadBalancerClient(name="provider-server",configuration= CustomLoadBalancerConfig.class)
public class ConsumerApplication15001 {

    /**
     * 使用spring提供的RestTemplate发送http请求调用微服务
     * 在主启动类中，将RestTemplate实例放入Spring容器中
     */
    @Bean
    @LoadBalanced//    实现负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication15001.class, args);
    }
}
