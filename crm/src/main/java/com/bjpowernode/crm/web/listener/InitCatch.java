package com.bjpowernode.crm.web.listener;

import com.bjpowernode.crm.pojo.Value;
import com.bjpowernode.crm.service.ValueService;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebListener
public class InitCatch implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("系统启动完成，初始化缓存数据...");
        WebApplicationContext factory = ContextLoader.getCurrentWebApplicationContext();
        ValueService valueService = factory.getBean(ValueService.class);
        List<Value> values = valueService.getAll();

        // 把values根据tid拆分成多个集合
        // key: tid， value: tid相同的数据集合
        Map<String, List<String>> map = new HashMap<>();

        for (Value value : values) {
            String key = value.getTid();
            List<String> valueList = map.get(key);
            // 说明value.getTid()对应的key第一次被遍历到
            if (valueList == null) {
                valueList = new ArrayList<>();
                map.put(key, valueList);
            }
            valueList.add(value.getText());
        }

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("dicMap", map);

        System.out.println("缓存加载完毕！");
    }
}
