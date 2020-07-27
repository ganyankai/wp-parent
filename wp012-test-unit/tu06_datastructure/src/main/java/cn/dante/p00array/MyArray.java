package cn.dante.p00array;

public class MyArray {
    private long[] arr;
    //有效数据的长度
    private int elements;

    public MyArray(){
        arr = new long[50];
    }

    public MyArray(int maxsize){
        arr = new long[maxsize];
    }

    /**
     * 添加数据
     */
    public void insert(long value){
        arr[elements] = value;
        elements ++;
    }

    /**
     * 显示数据
     */
    public void display(){
        System.out.print("[");
        for(int i=0;i<elements;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println("]");
    }
    //根据值查找
    public int search(long value){
        int i;
        for(i=0;i<elements;i++){
            if(value == arr[i]){
                break;
            }
        }
        if (i == elements){
            return -1;
        }else {
            return i;
        }
    }

    /**
     * 查找数据
     */
    public long get(int index){
        if(index>=elements || index <0){
            throw new ArrayIndexOutOfBoundsException();
        }else{
            return arr[index];
        }
    }

    /**
     * 删除数据
     */
    public void delete(int index){
        if(index>=elements || index <0){
            throw new ArrayIndexOutOfBoundsException();
        }
//        for (int i= index;i<elements;i++){
//            arr[i] = arr[i+1];
//        }

        //这是老师写的错代码
        for (int i= index;i<elements;i++){
            System.out.println("index:"+index);
            arr[index] = arr[index+1];
        }

//        以前写的
//        for(int i = index;i<elements-index-1;i++){
//            arr[i] =arr[i+1];
//        }
        elements --;
    }

    /**
     * 更新数据
     */
    public void change(int index,int newvalue){
        if(index>=elements || index <0){
            throw new ArrayIndexOutOfBoundsException();
        }
        arr[index] = newvalue;
    }

}
