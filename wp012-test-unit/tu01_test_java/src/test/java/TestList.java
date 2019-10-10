import java.util.Arrays;
import java.util.List;

public class TestList {

    //测试集合的索引值
    public static void main2(String[] args) {
        Integer[] integers = {11, 22, 33};
        List<Integer> list = Arrays.asList(integers);

        for (Integer integer:
             list) {
            System.out.println(list.indexOf(integer));
        }

    }

}
