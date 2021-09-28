package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.Transaction;
import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.service.ClueActivityService;
import com.bjpowernode.crm.service.ClueService;
import com.bjpowernode.crm.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("clue")
public class ClueController {

    @Autowired
    private ClueActivityService clueActivityService;

    @Autowired
    private ClueService clueService;

    @GetMapping("clueActivities.json")
    public List clueActivities(String clueId) {
        return clueActivityService.getByClueId(clueId);
    }

    @DeleteMapping("unbind.do")
    public Map unbind(String id) {
        clueActivityService.delete(id);
        return Result.SUCCESS;
    }

    @PutMapping("bind.do")
    public Map bind(String[] actIds, String clueId) {
        clueActivityService.bind(clueId, actIds);
        return Result.SUCCESS;
    }

    @PutMapping("convert.do")
    public Map convert(String clueId, Boolean createTran, Transaction transaction, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        String createBy = user.getLoginAct() + "|" + user.getName();
        clueService.convert(clueId, createTran, transaction, createBy);
        return Result.SUCCESS;
    }
}
