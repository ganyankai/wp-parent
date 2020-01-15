package cn.dante.pattern.ty06command;

public abstract class Command {
    //把三个组都定义好，子类可以直接使用
    protected RequirementGroup rg = new RequirementGroup(); //需求组
    protected PageGroup pg = new PageGroup(); //美工组
    protected CodeGroup cg = new CodeGroup(); //代码组;

    protected String status = "do";

    //只要一个方法，你要我做什么事情
    public abstract void execute();

    public  void executeReal(){
        if (status.equalsIgnoreCase("do")){
            execute();
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

}