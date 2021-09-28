package com.bjpowernode.crm.service;

import com.bjpowernode.crm.pojo.Activity;
import com.bjpowernode.crm.pojo.Page;

import java.util.List;

public interface ActivityService {
    void getPage(Page page);

    List<Activity> getAll();

    void saveList(List data);

    Activity get(String id);
}
