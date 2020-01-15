package cn.dante.pattern.ty09composite.v3;

public class Leaf extends Corp {
    //就写一个构造函数，这个是必须的
    public Leaf(String _name, String _position, int _salary) {
        super(_name, _position, _salary);
    }
}