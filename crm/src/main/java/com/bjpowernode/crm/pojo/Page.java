package com.bjpowernode.crm.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/*
    分页查询SQL: 关键语法：... limit start,length
        select * from xxx limit 0,10;  第1页
        select * from xxx limit 10,10; 第2页
        select * from xxx limit 20,10; 第3页
        ...
        select * from xxx limit (n-1)*每页记录数,每页记录数; 第n页

     分页的两个关键条件：
        第几页：currentPage
        每页显示多少条：rowsPerPage
 */
@Data
public class Page {
    private Integer currentPage = 1;        // 页码,默认是第1页
    private Integer rowsPerPage = 10;       // 每页显示的记录条数,默认每页10条

    private Integer maxRowsPerPage = 100;   // 每页最多显示的记录条数
    private Integer visiblePageLinks = 10;  // 显示几个卡片

    private Integer totalPages;             // 总页数
    private Integer totalRows;              // 总记录数
    private List data;                      // 分页数据

    // 接收搜索条件
    private Map search;
}
