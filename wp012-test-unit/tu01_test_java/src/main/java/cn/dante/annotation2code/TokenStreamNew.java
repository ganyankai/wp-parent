package cn.dante.annotation2code;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Vector;

class TokenStreamNew {
    Vector parseFromBrVector;
    int currentCharIndex;
    BufferedReader charInputSourceForParsing;
    int currentCharFromBr;

    TokenStreamNew(Reader read) {
        charInputSourceForParsing = new BufferedReader(read);
//        takeChar();
//        parseFromBrVector = parseFile();
        parseFromBrVector = parseTokensFromInputSource();
        currentCharIndex = 0;
    }

    Vector parseTokensFromInputSource() {
        Vector tokensParsedSoFar = new Vector();
        return tokensParsedSoFar;
    }
}