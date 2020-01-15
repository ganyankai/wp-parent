package cn.dante.pattern.ty05builder.v3;

import cn.dante.pattern.ty05builder.BMWModel;
import cn.dante.pattern.ty05builder.BenzModel;
import cn.dante.pattern.ty05builder.v2.BMWBuilder;
import cn.dante.pattern.ty05builder.v2.BenzBuilder;

import java.util.ArrayList;

public class Director {
    private ArrayList<String> sequence = new ArrayList();
    private BenzBuilder benzBuilder = new BenzBuilder();
    private BMWBuilder bmwBuilder = new BMWBuilder();

    /*
     * A类型的奔驰车模型，先start,然后stop,其他什么引擎了，喇叭一概没有
     */
    public BenzModel getABenzModel() {
//清理场景，这里是一些初级程序员不注意的地方
        this.sequence.clear();
//这只ABenzModel的执行顺序
        this.sequence.add("start");
        this.sequence.add("stop");
//按照顺序返回一个奔驰车
        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    /*
     * B型号的奔驰车模型，是先发动引擎，然后启动，然后停止，没有喇叭
     */
    public BenzModel getBBenzModel(){
        this.sequence.clear();
        this.sequence.add("engine boom");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel)this.benzBuilder.getCarModel();
    }

    public BMWModel getCBMWModel(){
        this.sequence.clear();
        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.bmwBuilder.setSequence(this.sequence);
        return (BMWModel)this.bmwBuilder.getCarModel();
    }

}