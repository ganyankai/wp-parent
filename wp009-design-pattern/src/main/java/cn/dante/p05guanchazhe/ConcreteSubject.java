package cn.dante.p05guanchazhe;

import javafx.event.Event;
//import java.awt.Event;

import java.util.Vector;

public class ConcreteSubject implements ISubject{
    Vector<IObserver> observers = new Vector<IObserver>();
    public void attach(IObserver observer) {
        observers.addElement(observer);
    }

    public void detach(IObserver observer) {
        observers.removeElement(observer);
    }

    public void inform() {
        Event evt = new Event(null);        //具体接口不详
        for (IObserver ob:observers
             ) {
            ob.update(evt);
        }
    }
}
