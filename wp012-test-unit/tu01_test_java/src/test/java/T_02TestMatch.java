public class T_02TestMatch {
    public static void main(String[] args) {
        String str = "134779076392";
        String pattern = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";

        System.out.println(RegexUtils.Regular(str,pattern));
    }


}
