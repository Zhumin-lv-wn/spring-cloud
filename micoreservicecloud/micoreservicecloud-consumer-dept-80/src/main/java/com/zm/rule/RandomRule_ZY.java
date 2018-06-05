package com.zm.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

public class RandomRule_ZY extends AbstractLoadBalancerRule {
     private int total=0;//当total==5以后，我们指针才能往下走
     private int currentIndex=0;//当前提供服务的服务器地址
    //分析：

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
    public Server choose(ILoadBalancer lb,Object key){
        if(lb==null){
           return null;
        }
        Server server=null;
        while (server==null){
            if(Thread.interrupted()){
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();
            int serverCount = allList.size();

            if(total<5){
                server=upList.get(currentIndex);
                total++;
            }else{
                total=0;
                currentIndex++;
                if(currentIndex>=upList.size()){
                    currentIndex=0;
                }
            }
            if(serverCount==0){
                return null;
            }
        }
        return server;
    }

    @Override
    public Server choose(Object o) {
       return choose(this.getLoadBalancer(),o);
    }
}
