package cn.dante.p00array;

public class TestMyOrderArray {
    public static void main(String[] args) {
        MyOrderArray arr = new MyOrderArray();
        arr.insert(1);
        arr.insert(3);
        arr.insert(5);
        arr.insert(7);
        arr.insert(9);

        arr.display();
        arr.insert(4);
//        arr.insert(8);
//        arr.insert(10);
//        arr.insert(11);
//        arr.insert(6);
        arr.display();
        System.out.println(arr.binarySearch(1));
        System.out.println(arr.binarySearch(4));
        System.out.println(arr.binarySearch(9));
    }
}
