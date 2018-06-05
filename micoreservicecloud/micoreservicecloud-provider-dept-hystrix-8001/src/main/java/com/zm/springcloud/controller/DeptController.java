package com.zm.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    //一旦服务方法失败抛出错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = deptService.get(id);
        if(null==dept){
            throw new RuntimeException("该ID:"+id+"没有相应的信息");
        }
        return dept;
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

    public Dept processHystrix_Get(@PathVariable("id") Long id){
        return new Dept().setDeptno(id).setDname("ID:"+id+"没有对应的消息，null--@HystrixCommand")
                .setDb_source("no this database in MySQL");
    }

}
