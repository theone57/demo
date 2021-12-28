package com.all.lin.spring.listener.event;

import com.all.lin.spring.listener.constant.EnumUserOperate;
import com.all.lin.spring.listener.pojo.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021-06-03 16:58
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description
 */
@Setter
@Getter
@Component
public class UserActionEvent extends ApplicationEvent {
     //操作是否成功
    private Boolean success;

    //操作类型
    private EnumUserOperate operate;

    //数据对象
    private User user;

    public UserActionEvent(@Qualifier("compositeDiscoveryClient") Object source) {
        super(source);
    }
}

