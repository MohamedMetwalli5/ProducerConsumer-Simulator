package eg.edu.alexu.csd.oop.mail;
public class LinkedBasedQ {
    public class Node{
        private Object element;
        private Node next;

        public Node(Object obj,Node n){
            element=obj;
            next=n;
        }

        public Node() {}

        public Object getElement() {
            return element;
        }

        public void setElement(Object obj) {
            element = obj;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node newNext) {
            next = newNext;
        }
    }
    private Node head;
    private Node rear;
    private int size;
    public void show(){
        Node node=head;
        for(int i=0;i<size;i++){
            System.out.println(node.getElement());
            node=node.getNext();
        }
    }

    public void enqueue(Object item) {
        if(item==null) {
            throw new NullPointerException();
        }
        Node node=new Node();
        node.setElement(item);
        if(size==0){
            head=node;
            rear=node;
        }else {
            rear.setNext(node);
        }
        rear=node;
        size++;
    }

    public Object peek(){
        if(isEmpty()){
            throw new RuntimeException("Empty Queue");
        }
        return head.getElement();
    }
    public Object dequeue() {
        if(isEmpty())
            throw new RuntimeException("Empty Queue");

        Object temp=head.getElement();
        head=head.getNext();
        size--;
        if(isEmpty())
            rear=null;
        return temp;

    }


    public boolean isEmpty() {
        if(size==0)
            return true;

        return false;
    }


    public int size() {
        return size;
    }
}