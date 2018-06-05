package com.zm.springcloud.service;

import com.zm.springcloud.entity.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 *
 */
//@FeignClient(value="MICROSERVICECLOUD-DEPT")
@FeignClient(value="MICROSERVICECLOUD-DEPT",fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

   @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") long id);
   @GetMapping("/dept/list")
    public List<Dept> list();
   @PostMapping("/dept/add")
    public boolean add(Dept dept);
}
