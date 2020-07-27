package cn.dante.p04linklist;

public class LinkList {
    //头结点
    private Node first;

    public LinkList(){
        first = null;
    }

    /**
     * 插入一个节点,在头结点后进行插入
     */
    public void insertFirst(long value){
        Node node = new Node(value);
        node.next = first;
        first = node;

//        if (first ==null){
//            first = node;
//        }else{
//            node.next = first.next;
//            first = node;
//        }
    };

    /**
     * 删除一个节点,在头结点后进行删除
     */
    public Node deleteFirst(){
        Node tmp = first;
        first = tmp.next;
        return tmp;
    }

    /**
     * 显示方法
     */
    public void display(){
        Node current = first;
        while(current !=null){
            current.display();
            current = current.next;
        }
        System.out.println();
    }

    /**
     * 查找方法
     */
    public Node find(long value){
        Node current = first;
        while(current.data !=value){
            current = current.next;
            if (current.next == null){
                return null;
            }
        }
        return current;
    }
}
