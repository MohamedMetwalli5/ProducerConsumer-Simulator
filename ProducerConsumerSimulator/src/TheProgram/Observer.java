package TheProgram;

public interface Observer {
     //Index of the machine which is ready
     void update(Observable machine,Boolean machineState);
     void sendProduct(Observable machine);
     LinkedBasedQ getQueue();
}
