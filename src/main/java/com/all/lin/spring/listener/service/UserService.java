package com.all.lin.spring.listener.service;

import com.all.lin.spring.listener.constant.EnumUserOperate;
import com.all.lin.spring.listener.event.UserActionEvent;
import com.all.lin.spring.listener.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author linpu
 * @dateTime 2021-06-03 17:11
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 发布者会调用 ApplicationEventPublisher的publishEvent 方法对某一事件进行发布。随后Spring容器会把该事件告诉所有的监听者（我的“女神”有动态了），监听者根据拿到的“信息、某些指令或者某些数据”去做一些业务上的操作。
     *
     * 这个模式常常会与设计模式中观察者模式进行对比。举个栗子：上课铃响了，老师和同学听到铃声后，都来班里了（老师要上课，学生要听课）。在这个事件里，被观察的是“铃声”，“铃声响了”是一种状态，或者说是一种通知。告诉大家：该上课了。
     * ————————————————
     * 版权声明：本文为CSDN博主「wshanshi」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/weixin_43770545/article/details/105971971
     *
     * Question
     * 1.业务类使用new userActionEvent(this); 实例化Event传递this目的是为了啥，有什么作用？
     * 2.监听器核心类，抛出异常return event; 你知道会造成死循环吗？为什么方法不定义void出现异常自己捕获处理
     *
     * @param user
     * @return
     */
    public User addUser(User user) {
        //todo此处自定义持久层..........
        //添加用户操作成功,发布通知,新增user

        UserActionEvent userActionEvent = new UserActionEvent(this);
        userActionEvent.setUser(user);
        userActionEvent.setOperate(EnumUserOperate.ADD);
        userActionEvent.setSuccess(true);

        applicationEventPublisher.publishEvent(userActionEvent);
        log.info("发布add事件:{}", userActionEvent);
        return user;
    }
}

