package cn.dante.removesmell.rs02;

public class DataTypeNew {
//     if (t == 0) { //raw data.
//        path = prefix+"raw"+suffix;
//        hidden = true;
//    } else if (t == 1) { //清空数据
//        path = "c:/application/data/cleanedUp.dat";
//        hidden = true;
//    } else if (t == 2) { //处理数据
//        path = "c:/application/data/processed.dat";
//        hidden = true;
//    } else if (t == 3) { //数据可以公布
//        path = "c:/application/data/publication.dat";
//        hidden = false;
//    }

    int typeCode;
    String typeStr;
    public DataTypeNew(int typeCode,String typeStr){
        this.typeCode = typeCode;
        this.typeStr=typeStr;
    }

//    static DataTypeNew raw = new DataTypeNew(0);
//    static DataTypeNew cleanedUp = new DataTypeNew(1);
//    static DataTypeNew processed = new DataTypeNew(2);
//    static DataTypeNew publication = new DataTypeNew(3);

//    private static DataTypeNew[] typeStrs = new DataTypeNew[]{
//            raw,cleanedUp,processed,publication
//    };

//    public int getTypeCode() {
//
//    }


}
