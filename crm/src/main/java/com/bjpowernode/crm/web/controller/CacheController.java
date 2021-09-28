package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cache")
public class CacheController {

    @GetMapping("options.json")
    public List options(String type, HttpServletRequest request) {
        Map<String, List<String>> map = (Map<String, List<String>>) request.getServletContext().getAttribute("dicMap");
        return map.get(type);
    }
}
