package cn.dante.p01stack;

import java.util.Arrays;

public class TestMyStack {
    public static void main(String[] args) {
        MyStack myStack = new MyStack(4);
        myStack.push(1);
        myStack.push(3);
        myStack.push(5);
        myStack.push(7);

//        System.out.println(myStack.isEmpty());
//        System.out.println(myStack.isFull());
//        System.out.println(Arrays.toString(myStack.arr));
//        myStack.pop();
//        System.out.println(Arrays.toString(myStack.arr));
//        System.out.println(myStack.peek());
        while(!myStack.isEmpty()){
            System.out.print(myStack.pop()+" ");
        }

    }
}
