package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.TransactionMapper;
import com.bjpowernode.crm.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionMapper transactionMapper;

    public List getECharts() {
        return transactionMapper.getECharts();
    }
}
