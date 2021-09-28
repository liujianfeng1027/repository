package com.bjpowernode.crm.pojo;

import lombok.Data;

@Data
public class Transaction {
    private String id;
    private String owner;
    private String amountOfMoney;
    private String name;
    private String expectedClosingDate;
    private String customerId;
    private String stage;
    private String type;
    private String source;
    private String activityId;
    private String contactsId;
    private String description;
    private String createBy;
    private String createTime;
    private String editBy;
    private String editTime;
    private String contactSummary;
    private String nextContactTime;

    private Customer customer;
    private Contacts contacts;

}
