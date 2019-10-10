public class T_01TestString {
    public static void main(String[] args) {
        String str = "";
        String s = new T_01TestString().sub2(str);

        System.out.println(s);
    }

    private String sub2(String str){
        try {
            return str.substring(0,2);
        }catch (Exception e){
            return str;
        }
    }
}
