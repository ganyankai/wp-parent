package cn.dante.pattern.ty06command;

public class AddCodeCommand extends Command{

    public void execute() {
        cg.find();
        cg.add();
        cg.plan();
    }

}
