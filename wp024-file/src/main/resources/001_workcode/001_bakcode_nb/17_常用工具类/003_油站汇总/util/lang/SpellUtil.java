package com.catt.common.util.lang;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.HashSet;
import java.util.Set;

public class SpellUtil {
	
    /**
     * 将文本转换为拼音
     * @author Czeming
     * @param str 转换字符串
     * @return 转换后拼音
     */
	public static String convertHanyuToPinyin(String str){
        if(str!=null && !str.trim().equalsIgnoreCase("")){
            char[] strChar ;
            strChar=str.toCharArray();
            //汉语拼音格式输出类
            HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();

            //输出设置，大小写，音标方式等
            hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

            String temp = "";
            for(int i=0;i<strChar.length;i++){
                char c = strChar[i];
                //是中文或者a-z或者A-Z转换拼音(我的需求，是保留中文或者a-z或者A-Z)
                if(String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")){
                    try{
                        temp += PinyinHelper.toHanyuPinyinStringArray(strChar[i], hanYuPinOutputFormat)[0];
                    }catch(BadHanyuPinyinOutputFormatCombination e) {
                        e.printStackTrace();
                    }
                }else if(((int)c>=65 && (int)c<=90) || ((int)c>=97 && (int)c<=122)){
                    temp += String.valueOf(strChar[i]).toLowerCase();
                }else{
                    temp += String.valueOf(strChar[i]);
                }
            }
            return temp;
        }
        return null;
	}

    /**
     * 将文本转换为拼音集合
     * @author Czeming
     * @param str
     * @return Set&lt;String&gt;
     */
    public static Set<String> convertHanyuToPinyinSet (String str){
        if(str!=null && !str.trim().equalsIgnoreCase("")){
            char[] strChar ;
            strChar=str.toCharArray();
            //汉语拼音格式输出类
            HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();

            //输出设置，大小写，音标方式等
            hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

            String[][] temp = new String[str.length()][];
            for(int i=0;i<strChar.length;i++){
                char c = strChar[i];
                //是中文或者a-z或者A-Z转换拼音(我的需求，是保留中文或者a-z或者A-Z)
                if(String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")){
                    try{
                        temp[i] = PinyinHelper.toHanyuPinyinStringArray(strChar[i], hanYuPinOutputFormat);
                    }catch(BadHanyuPinyinOutputFormatCombination e) {
                        e.printStackTrace();
                    }
                }else if(((int)c>=65 && (int)c<=90) || ((int)c>=97 && (int)c<=122)){
                    temp[i] = new String[]{String.valueOf(strChar[i]).toLowerCase()};
                }else{
                    temp[i] = new String[]{String.valueOf(strChar[i])};;
                }
            }
            String[] pingyinArray = Exchange(temp);
            Set<String> pinyinSet = new HashSet<String>();
            for(int i=0;i<pingyinArray.length;i++){
                pinyinSet.add(pingyinArray[i]);
            }
            return pinyinSet;
        }
        return null;
    }

    /**
     * 递归拼接拼音名称
     * @param strJaggedArray
     * @return
     */
    private static String[] Exchange(String[][] strJaggedArray){
        String[][] temp = DoExchange(strJaggedArray);
        return temp[0];
    }

    /**
     * 递归拼接拼音名称
     * @param strJaggedArray
     * @return
     */
    private static String[][] DoExchange(String[][] strJaggedArray){
        int len = strJaggedArray.length;
        if(len >= 2){
            int len1 = strJaggedArray[0].length;
            int len2 = strJaggedArray[1].length;
            int newlen = len1*len2;
            String[] temp = new String[newlen];
            int Index = 0;
            for(int i=0;i<len1;i++){
                for(int j=0;j<len2;j++){
                    temp[Index] = strJaggedArray[0][i] + strJaggedArray[1][j];
                    Index ++;
                }
            }
            String[][] newArray = new String[len-1][];
            for(int i=2;i<len;i++){
                newArray[i-1] = strJaggedArray[i];
            }
            newArray[0] = temp;
            return DoExchange(newArray);
        }else{
            return strJaggedArray;
        }
    }

    public static void main(String[] args) {
		System.out.println(com.catt.common.util.lang.SpellUtil.convertHanyuToPinyin("黄1￥%……###aAsd重"));
	}
}
