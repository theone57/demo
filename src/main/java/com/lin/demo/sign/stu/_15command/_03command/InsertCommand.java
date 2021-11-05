package com.lin.demo.sign.stu._15command._03command;

/**
 * 具体插入命令类
 * 
 * @author w1992wishes
 * @created @2017年11月3日-下午5:26:28
 *
 */
class InsertCommand extends Command {
    private static final long serialVersionUID = -6239610676788773397L;

    public InsertCommand(String name) {
        super(name);
    }

    @Override
    public void execute(String args) {
        this.args = args;
        operator.insert(args);
    }
}

/**
 * 具体删除命令类
 * 
 * @author w1992wishes
 * @created @2017年11月3日-下午5:29:19
 *
 */
class DeleteCommand extends Command {
    private static final long serialVersionUID = -4259959904986587353L;

    public DeleteCommand(String name) {
        super(name);
    }

    @Override
    public void execute(String args) {
        this.args = args;
        operator.delete(args);
    }
}

/**
 * 具体修改命令类
 * 
 * @author w1992wishes
 * @created @2017年11月3日-下午5:28:40
 *
 */
class ModifyCommand extends Command {
    private static final long serialVersionUID = -4259959904986587353L;

    public ModifyCommand(String name) {
        super(name);
    }

    @Override
    public void execute(String args) {
        this.args = args;
        operator.modify(args);
    }
}