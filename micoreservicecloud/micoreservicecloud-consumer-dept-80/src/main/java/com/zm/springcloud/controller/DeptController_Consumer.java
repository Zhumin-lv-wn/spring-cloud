package com.zm.springcloud.controller;

import com.zm.springcloud.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController_Consumer {
//    private static final String REST_URL_PREFIX="http://localhost:8001";
private static final String REST_URL_PREFIX="http://MICROSERVICECLOUD-DEPT";
    @Autowired
    private RestTemplate restTemplate;

   /* @Autowired
    private DiscoveryClient discoveryClient;*/

    @PostMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    @GetMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }

    @GetMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }

 /*   @GetMapping("/dept/discovery")
    public Object discovery(){
        List<String> list = discoveryClient.getServices();
        System.out.println("**********"+list);
        List<ServiceInstance> srvList = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
        for(ServiceInstance element : srvList){
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }
        return this.discoveryClient;
    }*/
}
