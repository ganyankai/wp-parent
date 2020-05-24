package cn.dante.t04neicunxielou;

public class HugeStr {
    private String str = new String(new char[100000]);

    public String getSubString(int begin,int end){
        return str.substring(begin,end);
    }
}
