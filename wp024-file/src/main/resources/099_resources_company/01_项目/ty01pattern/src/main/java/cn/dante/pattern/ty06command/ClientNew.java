package cn.dante.pattern.ty06command;

public class ClientNew {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        Command command = new AddRequirementCommand();
        invoker.setCommand(command);

//        command = new DeleteCommand();
//        invoker.setCommand(command);

        invoker.action();

    }
}