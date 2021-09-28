package com.bjpowernode.crm.mapper;

import java.util.List;

public interface ClueActivityMapper {
    List getByClueId(String clueId);

    void delete(String id);

    void deleteByClueId(String clueId);

    void saveList(List data);
}
