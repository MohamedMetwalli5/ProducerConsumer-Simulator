package eg.edu.alexu.csd.oop.mail;

public interface Observer {
     //Index of the machine which is ready
     void update(Observable machine,Boolean machineState);
     void setSubjectState(Observable machine);
     LinkedBasedQ getQueue();
}
