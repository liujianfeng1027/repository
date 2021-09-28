package com.bjpowernode.crm.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    // 查询总记录数
    Integer getCount();
    // 查询分页数据，关键语法：... limit start,length
    List getData(@Param("start") Integer start,
                 @Param("length") Integer length);
    int save(Object o);
}
