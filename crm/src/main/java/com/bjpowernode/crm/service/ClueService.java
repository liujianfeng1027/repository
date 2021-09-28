package com.bjpowernode.crm.service;

import com.bjpowernode.crm.pojo.Transaction;

public interface ClueService {
    void convert(String clueId, Boolean createTran, Transaction transaction, String createBy);
}
