package eg.edu.alexu.csd.oop.mail;

public interface Observable {
    void attachObserver(Observer q);
    void detachObserver(Observer q);
    void notifyObservers();
}
