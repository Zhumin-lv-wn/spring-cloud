package com.zm.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义ribbon规则类
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
//        return new RandomRule();随机算法
//    return new RoundRobinRule();//轮询算法
        return new RandomRule_ZY();
    }
}
