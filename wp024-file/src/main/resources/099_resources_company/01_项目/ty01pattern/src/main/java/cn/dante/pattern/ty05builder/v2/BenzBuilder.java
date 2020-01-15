package cn.dante.pattern.ty05builder.v2;

import cn.dante.pattern.ty05builder.BenzModel;
import cn.dante.pattern.ty05builder.CarModel;

import java.util.ArrayList;

public class BenzBuilder extends CarBuilder {
    private BenzModel benz = new BenzModel();

    @Override
    public CarModel getCarModel() {
        return this.benz;
    }

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.benz.setSequence(sequence);
    }
}