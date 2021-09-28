package com.bjpowernode.crm.mapper;

import com.bjpowernode.crm.pojo.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ActivityMapper {
    Integer getCount(@Param("search") Map search);

    List getData(@Param("search") Map search,
                 @Param("start") Integer start,
                 @Param("length") Integer length);

    int save(Activity activity);

    List<Activity> getAll();

    void saveList(List data);

    Activity get(String id);
}
