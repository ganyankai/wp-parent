package cn.dante.t04neicunxielou;

public class ImprovedHugeStr {
    private String str = new String(new char[100000]);

    public String getSubString(int begin,int end){
        return new String(str.substring(begin,end));
    }
}
