package cn.dante.p05guanchazhe;

import javafx.event.Event;

public class ConcreteObserver implements IObserver{

    public void update(Event event) {
        System.out.println("observer receive information");
    }
}
