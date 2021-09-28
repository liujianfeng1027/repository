package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.ClueActivityMapper;
import com.bjpowernode.crm.pojo.ClueActivity;
import com.bjpowernode.crm.service.ClueActivityService;
import com.bjpowernode.crm.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClueActivityServiceImpl implements ClueActivityService {
    @Autowired
    private ClueActivityMapper clueActivityMapper;

    public List getByClueId(String clueId) {
        return clueActivityMapper.getByClueId(clueId);
    }

    @Override
    public void delete(String id) {
        clueActivityMapper.delete(id);
    }

    @Override
    public void bind(String clueId, String[] actIds) {
        // 先清除关系
        clueActivityMapper.deleteByClueId(clueId);
        if (actIds != null && actIds.length > 0) {
            List data = new ArrayList();
            for (String actId : actIds) {
                ClueActivity clueActivity = new ClueActivity();
                clueActivity.setId(UUIDUtil.getUUID());
                clueActivity.setClueId(clueId);
                clueActivity.setActivityId(actId);
                data.add(clueActivity);
            }
            clueActivityMapper.saveList(data);
        }
    }
}
