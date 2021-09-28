package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.Activity;
import com.bjpowernode.crm.pojo.Page;
import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.service.ActivityService;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.web.Result;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("act")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("page.json")
    public Page page(Page page) {
        activityService.getPage(page);
        return page;
    }

    @GetMapping("export2.do")
    public void export2(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String filename = "D:/course/stage4/day12_springmvc05/02-登录权限控制&ajax登陆舰操作.avi";
        FileInputStream in = new FileInputStream(filename);
        byte[] bytes = new byte[1024];
        int len;

        String name = "02-登录权限控制&ajax登陆舰操作.avi";
        // 获取浏览器类型，通过请求头中的User-Agent来判断
        String ua = request.getHeader("User-Agent");
        boolean IE_LT11 = ua.contains("MSIE"); // IE11以下版本
        boolean IE11 = ua.contains("rv:11.0) like Gecko"); // IE11
        boolean Edge = ua.contains("Edge"); // win10自带的Edge浏览器
        // 如果是微软的浏览器，直接进行UTF-8编码
        if (IE_LT11 || IE11 || Edge) {
            name = URLEncoder.encode(name, "UTF-8");
            // java和浏览器对UTF-8编码方式有略微的不同：对于空格，java编码后的结果是加号，
            // 而浏览器的编码结果是%20，因此将+替换成%20, 这样浏览器才能正确解析空格
            name = name.replace("+", "%20");
        }
        // 标准浏览器使用Base64编码
        else {
            BASE64Encoder encoder = new BASE64Encoder();
            name = encoder.encode(name.getBytes("utf-8"));
            // =?utf-8?B?文件名?= 是告诉浏览器以Base64进行解码
            name = "=?utf-8?B?" + name + "?=";
        }

        // 告诉浏览器，不要解析文件，要以附件的形式下载
        response.setHeader("Content-Disposition", "attachment;filename=" + name);

        ServletOutputStream out = response.getOutputStream();
        while ((len = in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        out.close();
        in.close();
    }

    @GetMapping("export.do")
    public void export(HttpServletResponse response, HttpServletRequest request) throws Exception {
        // 创建一个空的excel文件
        HSSFWorkbook excel = new HSSFWorkbook();
        // 创建页签(sheet)
        HSSFSheet sheet = excel.createSheet();
        // 创建行:第一行一般是标题
        int rowIndex = 0;
        HSSFRow row = sheet.createRow(rowIndex++);
        // 创建单元格
        int cellIndex = 0;
        row.createCell(cellIndex++).setCellValue("名称");
        row.createCell(cellIndex++).setCellValue("所有者");
        row.createCell(cellIndex++).setCellValue("开始日期");
        row.createCell(cellIndex++).setCellValue("结束日期");

        // 准备数据
        List<Activity> data = activityService.getAll();
        for (Activity act : data) {
            row = sheet.createRow(rowIndex++);
            cellIndex = 0;
            row.createCell(cellIndex++).setCellValue(act.getName());
            row.createCell(cellIndex++).setCellValue(act.getOwner());
            row.createCell(cellIndex++).setCellValue(act.getStartDate());
            row.createCell(cellIndex++).setCellValue(act.getEndDate());
        }
        response.setHeader("Content-Disposition", "attachment;filename=xxx.xls");
        excel.write(response.getOutputStream());
        excel.close();
    }

    @PostMapping("import.do")
    public Map _import(MultipartFile upFile, HttpSession session) throws IOException {

        List data = new ArrayList();
        User user = (User)session.getAttribute("loginUser");
        String createBy = user.getLoginAct() + "|" + user.getName();
        String createTime = DateTimeUtil.getSysTime();

        HSSFWorkbook excel = new HSSFWorkbook(upFile.getInputStream());
        HSSFSheet sheet = excel.getSheetAt(0);
        int i = 1;
        HSSFRow row;
        while ((row = sheet.getRow(i++)) != null) {
            int j = 0;
            String name = row.getCell(j++).getStringCellValue();
            String owner = row.getCell(j++).getStringCellValue();
            String startDate = row.getCell(j++).getStringCellValue();
            String endDate = row.getCell(j++).getStringCellValue();

            Activity activity = new Activity();
            activity.setId(UUIDUtil.getUUID());
            activity.setName(name);
            activity.setOwner(owner);
            activity.setStartDate(startDate);
            activity.setEndDate(endDate);
            activity.setCreateBy(createBy);
            activity.setCreateTime(createTime);

            data.add(activity);
        }

        activityService.saveList(data);

        return Result.SUCCESS;
    }

    @GetMapping("activity.json")
    public Activity getAct(String id) {
        return activityService.get(id);
    }

    @GetMapping("activities.json")
    public List activities() {
        return activityService.getAll();
    }


}
