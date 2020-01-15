package cn.dante.pattern.ty06command;

public class DeleteCommand extends Command{

    public void execute() {
        super.setStatus("undo");
    }

}
