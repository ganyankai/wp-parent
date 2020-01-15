package cn.dante.pattern.ty06command;

public class AddRequirementCommand extends Command {
    //命令执行前如何撤回

    //执行增加一项需求的命令
    public void executerReal() {
    //找到需求组
        super.rg.find();
    //增加一份需求
        super.rg.add();
    //给出计划
        super.rg.plan();
    }


    public void execute() {
        if (super.status .equalsIgnoreCase("do"))
        executerReal();
    }

}
