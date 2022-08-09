package com.all.lin.statrter.mybatis;

import com.all.DemoApplication;
import com.all.lin.statrter.mybatis.sensitiveword.SensitiveWordMapper;
import com.all.lin.statrter.mybatis.user.AdminUserMapper;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

@SpringBootTest(classes = DemoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class MybatisTests {
    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Test
    public void contextLoads() {
//        SensitiveWordDO o = new SensitiveWordDO();
//        o.setTags(Arrays.asList("论坛", "蔬菜"));
//        o.setName("笨蛋");
////        sensitiveWordMapper.insert(o);
//
//        List<SensitiveWordDO> sensitiveWordDOS = sensitiveWordMapper.selectList();
//        sensitiveWordDOS.forEach(System.out::println);
//        LambdaQueryWrapper<SensitiveWordDO> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(SensitiveWordDO::getTags, "论坛,蔬菜");
//        SensitiveWordDO sensitiveWordDO = sensitiveWordMapper.selectOne(queryWrapper);
//        System.out.println("sensitiveWordDO = " + sensitiveWordDO);
//        List<AdminUserDO> adminUserDOS = adminUserMapper.selectList();
//        System.out.println("adminUserDOS = " + adminUserDOS);
        AdminUserDO adminUserDO = new AdminUserDO();
        adminUserDO.setAvatar("xxx");
        adminUserDO.setMobile("234");
        adminUserDO.setLoginIp("1111");
        adminUserDO.setDeptId(132L);
        adminUserDO.setUsername("sdfs");
        adminUserDO.setSex(1);
        adminUserDO.setNickname("sfsfds");
        HashSet<Long> postIds = Sets.newHashSet();
        postIds.add(2L);
        postIds.add(3L);
        adminUserDO.setPostIds(postIds);
        adminUserMapper.insert(adminUserDO);
    }

}