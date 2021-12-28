package com.all.lin.sign.stu._15command.lin.solider;

import com.all.lin.sign.stu._15command.lin.CommandContext;
import com.all.lin.sign.stu._15command.lin.thrid.MsgComponent;
import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021-10-19 14:36
 * @email im.linpu@qq.com
 * @description
 */
@Component
public class MsgSoldier implements Solider {

    @Override
    public void msg(CommandContext commandContext) {
        MsgComponent.sendMsg(commandContext.getMsgTypeEnum(), commandContext.getMsgParam());
    }
}

