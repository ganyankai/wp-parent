package cn.dante.pattern.ty05builder.v2;

import cn.dante.pattern.ty05builder.BMWModel;
import cn.dante.pattern.ty05builder.CarModel;

import java.util.ArrayList;

public class BMWBuilder extends CarBuilder {
    private BMWModel bmw = new BMWModel();

    @Override
    public CarModel getCarModel() {
        return this.bmw;
    }

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.bmw.setSequence(sequence);
    }
}