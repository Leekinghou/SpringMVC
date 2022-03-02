package com.springmvc.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: lijinhao
 * @date: 2022/03/02 14:24
 */

public class MyDateConverter implements Converter<String, Date> {
    public Date convert(String s){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = sdf.parse(s);
            return parse;
        } catch (ParseException e) {
            return null;
        }
    }
}
