package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author: lijinhao
 * @date: 2022/03/02 09:42
 */

@Controller
public class FormController {
    /**
     * 列表接收数值
     */
//    @PostMapping("/apply")
//    @ResponseBody
//    public String apply(String name, String course, Integer[] purpose){
//        System.out.println(name);
//        System.out.println(course);
//        for(Integer p: purpose){
//            System.out.println(p);
//        }
//        return "SUCCESS";
//    }
//
    /**
     * 集合接收数值
     */
//    @PostMapping("/apply")
//    @ResponseBody
//    public String apply(String name, String course, @RequestParam List<Integer> purpose){
//        System.out.println(name);
//        System.out.println(course);
//        for(Integer p: purpose){
//            System.out.println(p);
//        }
//        return "SUCCESS";
//    }

    /**
     * 关联对象赋值、注入
     */
//    @PostMapping("/apply")
//    @ResponseBody
//    public String apply(Form form){
//        System.out.println(form.getName());
//        System.out.println(form.getCourse());
//        for(Integer p: form.getPurpose()){
//            System.out.println(p);
//        }
//        System.out.println(form.getDelivery().getName());
//        return "SUCCESS";
//    }
//
//    // 当接受的数据没有符合数据时可以使用Map，但是有复合数据（列表、字典），就会丢失
//    public String apply1(@RequestParam Map map){
//        return "SUCCESS";
//    }

    /**
     * 时间格式转换
     */
//    @PostMapping("/apply")
//    @ResponseBody
//    public String apply(String name, String course, @DateTimeFormat(pattern = "yyyy-MM-dd") Date createTime){
//        System.out.println(name);
//        System.out.println(course);
//        return "SUCCESS";
//    }

    /**
     * 自定义转换器实现时间格式转换
     */
    @PostMapping("/apply")
    @ResponseBody
    public String apply(String name, String course, Date createTime){
        System.out.println(name);
        System.out.println(course);
        return "SUCCESS";
    }
}
