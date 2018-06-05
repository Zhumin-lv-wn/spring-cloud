package com.zm.springcloud.controller;

import com.zm.springcloud.entity.Dept;
import com.zm.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private DeptService deptService;

    @PostMapping("/dept/add")
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return deptService.get(id);
    }

    @GetMapping("/dept/list")
    public ResponseEntity<List<Dept>> list(){
        List<Dept> list = deptService.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/dept/discovery")
    public Object discovery(){
        List<String> list = discoveryClient.getServices();
        System.out.println("**********"+list);
        List<ServiceInstance> srvList = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
        for(ServiceInstance element : srvList){
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }
        return this.discoveryClient;
    }

}
