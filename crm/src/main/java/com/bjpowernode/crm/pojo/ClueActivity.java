package com.bjpowernode.crm.pojo;

import lombok.Data;

@Data
public class ClueActivity {
    private String id;
    private String clueId;
    private String activityId;

    private Activity activity;
}
