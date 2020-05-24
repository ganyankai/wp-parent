package cn.dante.t04neicunxielou;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> handler = new ArrayList<String>();
        for (int i = 0; i < 1000000 ; i++) {
            HugeStr h = new HugeStr();
//            ImprovedHugeStr h = new ImprovedHugeStr();
            handler.add(h.getSubString(1,5));

        }

    }

}
