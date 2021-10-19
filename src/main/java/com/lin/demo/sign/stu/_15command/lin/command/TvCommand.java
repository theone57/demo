package com.lin.demo.sign.stu._15command.lin.command;

import com.lin.demo.sign.stu._15command.lin.CommandContext;
import com.lin.demo.sign.stu._15command.lin.solider.MsgSoldier;
import com.lin.demo.sign.stu._15command.lin.solider.TvSolider;
import com.lin.demo.sign.stu._15command.lin.thrid.MsgComponent;
import com.lin.demo.sign.stu._15command.lin.thrid.MsgTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021-10-19 14:03
 * @email im.linpu@qq.com
 * @description
 */
@Component
public class TvCommand implements Command {
    @Autowired
    private TvSolider tvSolider;
    @Autowired
    private MsgSoldier msgSoldier;
    @Override
    public void execute(CommandContext context) {
        context.setMsgParam(new MsgComponent.MsgParam(context.getName(),context.getAge()));
        context.setMsgTypeEnum(MsgTypeEnum.TV);
        msgSoldier.msg(context);

        tvSolider.msg(context);
    }
}

