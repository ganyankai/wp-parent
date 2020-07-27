package cn.dante.p00array;

public class TestMyArray {
    public static void main(String[] args) {
        MyArray arr = new MyArray();
        arr.insert(13);
        arr.insert(34);
        arr.insert(90);
//        arr.insert(100);

        arr.display();
        System.out.println(arr.search(90));
        System.out.println(arr.get(0));
        arr.delete(0);
        arr.display();

//        arr.change(0,12);
//        arr.display();
    }
}
