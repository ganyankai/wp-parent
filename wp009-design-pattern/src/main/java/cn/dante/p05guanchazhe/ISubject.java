package cn.dante.p05guanchazhe;

public interface ISubject {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void inform();
}
