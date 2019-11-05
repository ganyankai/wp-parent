import java.math.BigDecimal;

public class TestNumberUtil {


//    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE);      //2147483647
//    }
    public static void main(String[] args) {
        Double round = NumberUtil.round(0.0001, 4);
        String s = toBigDecimalStr(round);
        System.out.println(s);      //0.0
    }

    public static void main6(String[] args) {
//        Double s = 0.0001;
        BigDecimal s = new BigDecimal(((Double)0.0001+""));
        System.out.println(s.toString());      //0.0
    }

    public static void main5(String[] args) {
        String s = new BigDecimal(NumberUtil.round(0.0001, 4).toString()).toString();
//        System.out.println( ((Double)0.0001+" ").toString());
        System.out.println(s);      //0.0
    }

    public static void main4(String[] args) {
        String s = NumberUtil.round(0.00001, 4).toString();
        System.out.println(s);      //0.0
    }

    public static void main3(String[] args) {
        String s = NumberUtil.round(0.0001, 4).toString();
        System.out.println(s);      //1.0E-4
    }

    public static void main2(String[] args) {

        Double round = NumberUtil.round( 0.0001, 4);
        System.out.println(round);      //1.0E-4
    }

    private static String toBigDecimalStr(Object o){
        return new BigDecimal(o.toString()).toString();
    }
}
