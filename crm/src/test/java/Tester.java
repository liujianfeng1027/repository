import com.bjpowernode.crm.mapper.*;
import com.bjpowernode.crm.pojo.Activity;
import com.bjpowernode.crm.pojo.Dept;
import com.bjpowernode.crm.pojo.Transaction;
import com.bjpowernode.crm.pojo.Value;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class Tester {
    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private ValueMapper valueMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ClueActivityMapper clueActivityMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Test
    public void test01() throws JsonProcessingException {
        Value value = new Value();
        value.setId(UUIDUtil.getUUID());
        value.setTid("color");
        value.setValue("red");
        value.setText("红");
        valueMapper.save(value);
    }

    @Test
    public void test02() {
        Value value = new Value();
        value.setId("4ae597319b334d85bddcc04d5ff8c676");
        value.setTid("color");
        value.setValue("red");
        value.setText("红");
        value.setOrderNo("1");
        valueMapper.update(value);
    }

    @Test
    public void test03() {
        for (int i = 0; i < 123; i++) {
            Dept dept = new Dept();
            dept.setId(UUIDUtil.getUUID());
            dept.setName("部门" + (i + 1));
            deptMapper.save(dept);
        }
    }

    @Test
    public void test04() {
        Integer count = deptMapper.getCount();
        System.out.println(count);
    }

    @Test
    public void test05() {
        List data = deptMapper.getData(0, 10);
        System.out.println(data);
    }

    @Test
    public void test06() {
        Random random = new Random();

        for (int i = 0; i < 123; i++) {
            Activity activity = new Activity();
            activity.setId(UUIDUtil.getUUID());
            activity.setOwner("10000|张三");
            activity.setName("发传单" + i);
            activity.setStartDate("2021-0" + (random.nextInt(9) + 1) + "-0" + (random.nextInt(9) + 1));
            activity.setEndDate("2022-0" + (random.nextInt(9) + 1) + "-1" + (random.nextInt(9) + 1));
            activityMapper.save(activity);
        }
    }

    @Test
    public void test07() {
        Map search = new HashMap();
        search.put("name", "3");
        search.put("startDate", "2021-07-02");
        search.put("endDate", "2021-07-02");
        search.put("owner", "三");

        Integer count = activityMapper.getCount(search);
        System.out.println(count);
        activityMapper.getData(search, 1, 10);
    }

    @Test
    public void test08() {
        Map<String, Value> search = new HashMap();
        System.out.println(search.get("xxx"));
    }

    @Test
    public void test09() {
        List list = clueActivityMapper.getByClueId("1");
        System.out.println(list);
    }

    @Test
    public void test10() {
        String[] stages = {
                "07成交",
                "01资质审查",
                "08丢失的线索",
                "09因竞争丢失关闭",
                "02需求分析",
                "05提案/报价",
                "03价值建议",
                "04确定决策者",
                "06谈判/复审"
        };

        Random random = new Random();
        int range = stages.length;

        for (int i = 0; i < 30; i++) {
            Transaction transaction = new Transaction();
            transaction.setId(UUIDUtil.getUUID());
            transaction.setName("交易" + i);
            transaction.setStage(stages[random.nextInt(range)]);
            transactionMapper.save(transaction);
        }
    }
}
