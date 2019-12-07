package cn.dante.shilihua;

public class TestTryCatch {
    public static void main(String[] args)
    {
        TestTryCatch test = new TestTryCatch();
        System.out.println(test.fun());
    }

    public StringBuilder fun()
    {
        StringBuilder s = new StringBuilder("Hello");
        try
        {
            //doing something
            s.append("Word");
            System.out.println("1r");
            return hhh(s);
        }catch(Exception e){
            return s;
        }finally{
            System.out.println("2f");
            s.append("finally");
        }
    }
    public StringBuilder hhh(StringBuilder s){
        System.out.println("3r");
        return s;
    }
}