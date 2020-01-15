package cn.dante.pattern.ty05builder.v3;

import cn.dante.pattern.ty05builder.BenzModel;

import java.util.ArrayList;

public class Client {
    public static void main(String[] args) {
        Director director = new Director();

        BenzModel aBenzModel = director.getABenzModel();

        ArrayList sequence = new ArrayList();
        sequence.add("stop"); //启动起来
        sequence.add("start"); //开了一段就停下来

        aBenzModel.setSequence(sequence);
        aBenzModel.run();
    }

}
