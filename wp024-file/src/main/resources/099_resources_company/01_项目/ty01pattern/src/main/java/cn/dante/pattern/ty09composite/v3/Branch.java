package cn.dante.pattern.ty09composite.v3;

import java.util.ArrayList;

public class Branch extends Corp {
    //领导下边有那些下级领导和小兵
    ArrayList<Corp> subordinateList = new ArrayList<Corp>();

    //构造函数是必须的了
    public Branch(String _name, String _position, int _salary) {
        super(_name, _position, _salary);
    }

    //增加一个下属，可能是小头目，也可能是个小兵
    public void addSubordinate(Corp corp) {
        corp.setParent(this); // 设置父节点

        this.subordinateList.add(corp);
        corp.setSeq(this.subordinateList.size());
    }

    //我有哪些下属
    public ArrayList<Corp> getSubordinate() {
        return this.subordinateList;
    }


}