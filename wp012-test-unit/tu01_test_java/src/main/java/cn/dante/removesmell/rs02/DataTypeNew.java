package cn.dante.removesmell.rs02;

public class DataTypeNew {

    int typeCode;
    String typeStr;
    Boolean hidden;
    public DataTypeNew(int typeCode,String typeStr,Boolean hidden){
        this.typeCode = typeCode;
        this.typeStr=typeStr;
        this.hidden = hidden;
    }

    public DataTypeNew(String typeStr,Boolean hidden){
        this.typeStr=typeStr;
        this.hidden = hidden;
    }

    String getSavePath() {
        return "c:/application/data/"+typeStr+".dat";
    }

    static DataTypeNew raw = new DataTypeNew("raw",true);
    static DataTypeNew cleanedUp = new DataTypeNew("cleanedUp",true);
    static DataTypeNew processed = new DataTypeNew("processed",true);
    static DataTypeNew publication = new DataTypeNew("publication",false);


}
