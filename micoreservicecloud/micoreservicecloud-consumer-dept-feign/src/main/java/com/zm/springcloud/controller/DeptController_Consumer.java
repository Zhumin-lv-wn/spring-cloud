package com.zm.springcloud.controller;

import com.zm.springcloud.entity.Dept;
import com.zm.springcloud.service.DeptClientService;
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
private static final String REST_URL_PREFIX="http://MICROSERVICECLOUD-DEPT";
     @Autowired
     private DeptClientService service;

     @GetMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
         return service.get(id);
     }

     @GetMapping("/consumer/dept/list")
    public List<Dept> list(){
         return service.list();
     }
     @PostMapping("/consumer/dept/add")
    public Object add(Dept dept){
         return this.service.add(dept);
     }
}
