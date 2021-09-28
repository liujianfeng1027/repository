package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tran")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("charts.json")
    public List charts() {
        return transactionService.getECharts();
    }
}
