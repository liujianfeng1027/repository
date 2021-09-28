package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.pojo.Transaction;
import com.bjpowernode.crm.service.ClueService;
import org.springframework.stereotype.Service;

@Service
public class ClueServiceImpl implements ClueService {
    @Override
    public void convert(String clueId, Boolean createTran, Transaction transaction, String createBy) {
        // 查询线索信息

        // 查询线索备注信息

        // 查询线索和市场活动的关联信息

        // 从线索中提取和客户相关的数据，封装客户对象，添加到客户表

        // 将线索备注表中的数据备份到客户备注表中

        // 从线索中提取联系人信息，添加联系人

        // 从线索备注中的数据备份到联系人备注中

        // 将线索和市场活动的关联关系，备份到联系人和市场活动的关系表中

        // 如果createTran为true
            // 添加交易

            // 添加交易历史

        // 删除线索备注

        // 删除线索和市场活动的关联关系

        // 删除线索

        // over
    }
}
