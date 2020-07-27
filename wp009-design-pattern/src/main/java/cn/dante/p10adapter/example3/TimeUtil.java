package cn.dante.p10adapter.example3;

public class TimeUtil {
    private long a1;
    private long a2;

    public void begin(){
        a1 = System.currentTimeMillis();
    }

    public void end(){
        a2 = System.currentTimeMillis();
    }

    public void show(){
        System.out.println("times==="+(a2-a1));
    }

}
