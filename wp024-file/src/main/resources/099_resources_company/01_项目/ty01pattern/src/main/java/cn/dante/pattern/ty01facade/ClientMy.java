package cn.dante.pattern.ty01facade;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * 我开始给朋友写信了
 */
public class ClientMy {
    public static void main(String[] args) {
    //创建一个处理信件的过程
        LetterProcessMy letterProcess = new LetterProcessImplMy();
        letterProcess.clientLetter("hello","world");
    }

}