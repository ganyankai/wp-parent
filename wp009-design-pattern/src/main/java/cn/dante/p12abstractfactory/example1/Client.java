package cn.dante.p12abstractfactory.example1;

public class Client {
    public static void main(String[] args) {
        ComputerEngineer engineer = new ComputerEngineer();
        engineer.makeComputer(1,1);
    }
}
