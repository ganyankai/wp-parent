package cn.dante.p00array;

public class MyOrderArray {
    private long[] arr;
    //有效数据的长度
    private int elements;

    public MyOrderArray(){
        arr = new long[50];
    }

    public MyOrderArray(int maxsize){
        arr = new long[maxsize];
    }

    /**
     * 添加数据
     */
    public void insert(long value){
//        if (elements==0){
//            arr[elements] = value;
//            elements ++;
//            return;
//        }

        for (int i= 0;i<elements+1;i++){
            boolean flag = false;
            if(arr[i]>value){
                flag = true;
                //结合具体的数据理解循环会让其变得简单
                for (int j = elements;j>i;j--){
                    arr[j] = arr[j-1];
                 }
                arr[i] = value;
                break;
            }
            if (!flag){
                arr[elements] = value;
            }
        }
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

    /**
     * 二分法查找数据
     */
    public int binarySearch(long value){
        int middle = 0;
        int low = 0;
        int pow = elements;

        //这个代码跟着老师写的,只能明白大概逻辑
        //学习要结合具体难度,具体业务才能学好
        while (true){
            middle = (pow+low)/2;
            if (arr[middle] == value){
                return middle;
            }else if(low > pow){
                return -1;
            }else {
                if(arr[middle] > value){
                    pow = middle -1;
                }else{
                    low = middle +1;
                }
            }
        }
//        return 0;
    }

}
