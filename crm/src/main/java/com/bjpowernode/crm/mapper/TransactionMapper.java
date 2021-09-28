package com.bjpowernode.crm.mapper;

import com.bjpowernode.crm.pojo.Transaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TransactionMapper {
    Integer getCount(@Param("search") Map search);

    List getData(@Param("search") Map search,
                 @Param("start") Integer start,
                 @Param("length") Integer length);
    int save(Transaction transaction);
    List getECharts();
}
