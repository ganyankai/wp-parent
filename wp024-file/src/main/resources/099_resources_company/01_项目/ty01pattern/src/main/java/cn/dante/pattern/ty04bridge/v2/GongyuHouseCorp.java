package cn.dante.pattern.ty04bridge.v2;

public class GongyuHouseCorp extends HouseCorp {
    //定义传递一个House产品进来
    public GongyuHouseCorp(House house) {
        super(house);
    }
    
    //房地产公司很High了，赚钱，计算利润
    public void makeMoney() {
        super.makeMoney();
        System.out.println("公寓房地产公司赚大钱了...");
    }

}