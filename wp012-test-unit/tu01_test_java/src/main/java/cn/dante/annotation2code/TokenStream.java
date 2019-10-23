package cn.dante.annotation2code;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Vector;

class TokenStream {
    Vector parseFromBrVector;
    int currentCharIndex;
    BufferedReader br; //从这里,读取字符串,解析出所有词符.
    int currentCharFromBr;

    //从 reader 中读取字符,解析出所有词符
    TokenStream(Reader read) {
        br = new BufferedReader(read);
//        takeChar();
        parseFromBrVector = parseFile();
        currentCharIndex = 0;
    }

    //从 br 中读取字符,解析出所有词符并存放在一个 venctor 中.
    Vector parseFile() {
        Vector v = new Vector(); //堆积所有已经解析出的词符
        return v;
    }
}