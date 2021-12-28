package com.all.lin.sign.stu._15command.lin.command;

import com.all.lin.sign.stu._15command.lin.solider.MsgSoldier;
import com.all.lin.sign.stu._15command.lin.solider.TvSolider;
import com.all.lin.sign.stu._15command.lin.CommandContext;
import com.all.lin.sign.stu._15command.lin.thrid.MsgComponent;
import com.all.lin.sign.stu._15command.lin.thrid.MsgTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021-10-19 14:07
 * @email im.linpu@qq.com
 * @description
 */
@Component
public class HomeCommand implements Command {
    @Autowired
    private LightCommand lightCommand;
    @Autowired
    private TvSolider tvSolider;
    @Autowired
    private MsgSoldier msgSoldier;

    @Override
    public void execute(CommandContext context) {
        context.setMsgParam(new MsgComponent.MsgParam(context.getName(),context.getAge()));
        context.setMsgTypeEnum(MsgTypeEnum.Home);
        msgSoldier.msg(context);

        lightCommand.execute(context);
        tvSolider.on(context);
    }
}

