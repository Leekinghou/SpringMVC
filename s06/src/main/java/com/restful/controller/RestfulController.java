package com.restful.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author: lijinhao
 * @date: 2022/03/03 10:59
 */
@RestController
@RequestMapping("/restful")
public class RestfulController {
    @GetMapping("/request")
    // 直接返回string结果
//    @ResponseBody
    public String doGetRequest(){
        return "{\"message\":\"返回查询结果\"}";
    }

    @PostMapping("/request")
    // 直接返回string结果
//    @ResponseBody
    public String doPostRequest(){
        return "{\"message\":\"数据新建成功\"}";
    }

    @PostMapping("/request/{rid}")
    public String doPostRequest1(@PathVariable("rid") Integer requestId){
        return "{\"message\":\"数据新建成功\", \"id\":" + requestId + "}";
    }

    @PutMapping("/request")
    // 直接返回string结果
//    @ResponseBody
    public String doPutRequest(){
        return "{\"message\":\"数据更新成功\"}";
    }

    @DeleteMapping("/request")
    // 直接返回string结果
//    @ResponseBody
    public String doDeleteRequest(){
        return "{\"message\":\"数据删除成功\"}";
    }
}
