package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("page.json")
    public Page page(Page page) {
        deptService.getPage(page);
        return page;
    }
}
