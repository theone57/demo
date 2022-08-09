package com.all.lin.statrter.listener.monitor;

import com.all.lin.statrter.listener.event.UserActionEvent;
import com.all.lin.statrter.listener.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021-06-03 17:21
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description 自定义监听器
 */
@Slf4j
@Component
public class UserMonitor {
    /**
     * 自定义监听方法上方添加注解：@EventListener()。
     * <p>
     * 眼尖的小伙伴会发现，楼主这里使用表达式condition = "#event.operate.name()==‘ADD’"对监听进行了细化：监听类型为“新增”的事件。
     * <p>
     * 注意：自定义监听必须交给spring容器管理，否则不起作用哈。如下图加@Component注解就行（兄弟，交保护费了。额…不交也行，但是必须得跟着spring混…）
     *
     * @param event
     * @return
     * @Async()会在下面说 ————————————————
     * 版权声明：本文为CSDN博主「wshanshi」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/weixin_43770545/article/details/105971971
     */
    @EventListener(value = UserActionEvent.class, condition = "#event.operate.name() == 'ADD'")
    public UserActionEvent addUserApplicationEvent(UserActionEvent event) {
        try {
            User user = event.getUser();
            log.info("监听到新增用户:{}", user);
            //自定义处理
            log.info("此处课程操作: 发送邮件/ 赠送用户体验卡......");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("事件:{} , 异常信息", event, e.getMessage());
            return event;
        }

        return null;
    }
}

