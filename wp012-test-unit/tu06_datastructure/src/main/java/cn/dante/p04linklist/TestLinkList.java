package cn.dante.p04linklist;

public class TestLinkList {
    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        linkList.insertFirst(1);
        linkList.insertFirst(3);
        linkList.insertFirst(5);

//        linkList.display();
//        linkList.deleteFirst();
//        linkList.display();

        Node node = linkList.find(3);
        node.display();
    }
}
