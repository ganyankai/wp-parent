package cn.dante.pattern.ty02adapter;

public class SuperUser implements ISuperUser{

    public String fly() {
        System.out.println("i can fly");
        return "super fly";
    }

}
