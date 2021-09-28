package com.bjpowernode.crm.service;

import java.util.List;

public interface ClueActivityService {
    List getByClueId(String clueId);

    void delete(String id);

    void bind(String clueId, String[] actIds);
}
