package cn.dante.pattern.ty10_observer.v3;

public interface Observer {
    //一发现别人有动静，自己也要行动起来
    public void update(String context);
}