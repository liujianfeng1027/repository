package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.ActivityMapper;
import com.bjpowernode.crm.pojo.Activity;
import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public void getPage(Page page) {
        Integer totalRows = activityMapper.getCount(page.getSearch()); // 总记录数
        // 计算总页数
        Integer rowsPerPage = page.getRowsPerPage(); // 每页条数
        Integer totalPages = (totalRows - 1) / rowsPerPage + 1;

        // 计算开始索引：limit start:(n-1)*每页记录数,每页记录数
        Integer start = (page.getCurrentPage() - 1) * rowsPerPage;
        List data = activityMapper.getData(page.getSearch(), start, rowsPerPage);

        page.setTotalRows(totalRows);
        page.setTotalPages(totalPages);
        page.setData(data);
    }

    public List<Activity> getAll() {
        return activityMapper.getAll();
    }

    public void saveList(List data) {
        activityMapper.saveList(data);
    }

    public Activity get(String id) {
        return activityMapper.get(id);
    }
}
