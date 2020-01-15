package cn.dante.pattern.ty03template;

public class HummerH1Model extends HummerModel {


    @Override
    public void alarm() {
        System.out.println("悍马H1鸣笛...");
    }

    @Override
    public void engineBoom() {
        System.out.println("悍马H1引擎声音是这样在...");
    }

    @Override
    public void start() {
        System.out.println("悍马H1发动...");
    }

    @Override
    public void stop() {
        System.out.println("悍马H1停车...");
    }
    //钩子方法，默认喇叭是会响的
    protected boolean isAlarm(){
        return true;
    }


}