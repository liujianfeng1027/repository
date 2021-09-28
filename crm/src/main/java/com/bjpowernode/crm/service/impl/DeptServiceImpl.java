package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.DeptMapper;
import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    public void getPage(Page page) {
        /*
            private Integer totalPages;             // 总页数
            private Integer totalRows;              // 总记录数
            private List data;                      // 分页数据
         */
        Integer totalRows = deptMapper.getCount(); // 总记录数
        // 计算总页数
        Integer rowsPerPage = page.getRowsPerPage(); // 每页条数
        Integer totalPages = (totalRows - 1) / rowsPerPage + 1;

        // 计算开始索引：limit start:(n-1)*每页记录数,每页记录数
        Integer start = (page.getCurrentPage() - 1) * rowsPerPage;
        List data = deptMapper.getData(start, rowsPerPage);

        page.setTotalRows(totalRows);
        page.setTotalPages(totalPages);
        page.setData(data);
    }
}
