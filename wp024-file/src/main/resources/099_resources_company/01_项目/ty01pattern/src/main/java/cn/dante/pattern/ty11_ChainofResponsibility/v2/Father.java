package cn.dante.pattern.ty11_ChainofResponsibility.v2;

import cn.dante.pattern.ty11_ChainofResponsibility.IWomen;

public class Father extends Handler {
    //父亲只处理女儿的请求
    public Father() {
        super(1);
    }

    //父亲的答复
    @Override
    public void response(IWomen women) {
        System.out.println("--------女儿向父亲请示-------");
        System.out.println(women.getRequest());
        System.out.println("父亲的答复是:同意\n");
    }

}