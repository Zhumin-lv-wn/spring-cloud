package com.zm.springcloud.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.zm.rule.RandomRule_ZY;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {//boot -->spring applicationContext.xml--@Configuration 配置
    //ConfigBean=applicationContext.txt;

    @Bean
    @LoadBalanced//启动负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public IRule myRule(){
//        return new RoundRobinRule();//打到目的，用我们重新选中的随机算法代替默认的轮询
//        return new RandomRule();
        return new RandomRule_ZY();
    }
}

//<bean id="userService" class="com.zm.tmall.UserServiceImpl"/>
