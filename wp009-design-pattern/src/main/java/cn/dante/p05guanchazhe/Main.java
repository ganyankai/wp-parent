package cn.dante.p05guanchazhe;


public class Main {
    public static void main(String[] args) {
       ISubject subject = new ConcreteSubject();
       IObserver observer = new ConcreteObserver();
       IObserver observer2 = new ConcreteObserver();
       subject.attach(observer);
       subject.attach(observer2);

       subject.inform();
    }
}
