package TheProgram;

public interface Observable {
    void attachObserver(Observer q);
    void detachObserver(Observer q);
    void notifyObservers();
    void notifyApp();
}
