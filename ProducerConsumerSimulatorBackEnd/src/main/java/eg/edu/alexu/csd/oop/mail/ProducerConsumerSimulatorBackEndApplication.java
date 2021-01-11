package eg.edu.alexu.csd.oop.mail;


import java.util.ArrayList;

public class ProducerConsumerSimulatorBackEndApplication {


	public static void main(String args[]){
		LinkedBasedQ q0=new LinkedBasedQ();
		int productsNumber=7;
		ArrayList<MachineRunner>machines=new ArrayList<>();
		ArrayList<QueueRunner> queues=new ArrayList<>();

		LinkedBasedQ q1=new LinkedBasedQ();
		LinkedBasedQ q3=new LinkedBasedQ();
		LinkedBasedQ q4=new LinkedBasedQ();
		LinkedBasedQ q5=new LinkedBasedQ();
		LinkedBasedQ q6=new LinkedBasedQ();
		Machine m1=new Machine();
		Machine m2=new Machine();
		Machine m3=new Machine();
		Machine m4=new Machine();
		Machine m5=new Machine();
		Machine m6=new Machine();
		Machine m7=new Machine();

		ArrayList<Machine>machinesQ0=new ArrayList<>();
		ArrayList<Machine>machinesQ1=new ArrayList<>();
		ArrayList<Machine>machinesQ3=new ArrayList<>();
		ArrayList<Machine>machinesQ4=new ArrayList<>();
		ArrayList<Machine>machinesQ5=new ArrayList<>();

		//Setting Qout
		m1.setQout(q1);
		m2.setQout(q3);
		m3.setQout(q3);
		m4.setQout(q4);
		m5.setQout(q5);
		m6.setQout(q6);
		m7.setQout(q6);
		//Setting Qin
		machinesQ0.add(m1);
		machinesQ0.add(m4);
		machinesQ1.add(m2);
		machinesQ1.add(m3);
		machinesQ3.add(m5);
		machinesQ4.add(m6);
		machinesQ4.add(m7);
		machinesQ5.add(m6);
		machinesQ5.add(m7);

		Queue moQ0=new Queue(q0,machinesQ0);
		Queue moQ1=new Queue(q1,machinesQ1);
		Queue moQ3=new Queue(q3,machinesQ3);
		Queue moQ4=new Queue(q4,machinesQ4);
		Queue moQ5=new Queue(q5,machinesQ5);


		ArrayList<Observer>queuesM1=new ArrayList<>();
		ArrayList<Observer>queuesM2=new ArrayList<>();
		ArrayList<Observer>queuesM3=new ArrayList<>();
		ArrayList<Observer>queuesM4=new ArrayList<>();
		ArrayList<Observer>queuesM5=new ArrayList<>();
		ArrayList<Observer>queuesM6=new ArrayList<>();
		ArrayList<Observer>queuesM7=new ArrayList<>();


		queuesM1.add(moQ0);
		queuesM2.add(moQ1);
		queuesM3.add(moQ1);
		queuesM4.add(moQ0);
		queuesM5.add(moQ3);
		queuesM6.add(moQ4);
		queuesM6.add(moQ5);
		queuesM7.add(moQ4);
		queuesM7.add(moQ5);

		m1.setQin(queuesM1);
		m2.setQin(queuesM2);
		m3.setQin(queuesM3);
		m4.setQin(queuesM4);
		m5.setQin(queuesM5);
		m6.setQin(queuesM6);
		m7.setQin(queuesM7);

		machines.add(new MachineRunner(m1));
		machines.add(new MachineRunner(m2));
		machines.add(new MachineRunner(m3));
		machines.add(new MachineRunner(m4));
		machines.add(new MachineRunner(m5));
		machines.add(new MachineRunner(m6));
		machines.add(new MachineRunner(m7));
		queues.add(new QueueRunner(moQ0));
		queues.add(new QueueRunner(moQ1));
		queues.add(new QueueRunner(moQ3));
		queues.add(new QueueRunner(moQ4));
		queues.add(new QueueRunner(moQ5));

		MainClass main=new MainClass();

		main.simulate(7,q0,machines,queues);
		while(true){
			if(q6.size()==7){
				int i;
				for(i=0;i<machines.size();i++){
					machines.get(i).setKillThread(true);
				}
				for(i=0;i<queues.size();i++){
					queues.get(i).setKillThread(true);
				}
				if(i==7){
					break;
				}
			}
			/*else {
				System.out.println("==========================");
				System.out.println(q6.size());
				System.out.println("==========================");
			}*/
		}
	}

	/*public String produce(){
		return main.produce();
	}

	public void createMachine(){
		main.createMachine();
	}

	public void createQueue(){
		main.createQueue();
	}

	public void addInQueue(@RequestBody String indices){//TODO consistent with request sent from frontend (name is machineIndex and queueIndex)
		ObjectMapper mapper=new ObjectMapper();
		Map<String,Integer> indicesMap=null;
		try {
			indicesMap=(Map<String,Integer>)mapper.readValue(indices, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		main.addInQueue(indicesMap.get("machineIndex"),indicesMap.get("queueIndex"));
	}

	public void addOutQueue(@RequestBody String indices){//Todo consistent with request sent from frontend (name is machineIndex and queueIndex)
		ObjectMapper mapper=new ObjectMapper();
		Map<String,Integer> indicesMap=null;
		try {
			indicesMap=(Map<String,Integer>)mapper.readValue(indices, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		main.addOutQueue(indicesMap.get("machineIndex"),indicesMap.get("queueIndex"));
	}*/

}