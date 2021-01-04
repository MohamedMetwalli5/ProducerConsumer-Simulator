package eg.edu.alexu.csd.oop.mail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.Map;
 	class runner implements Runnable{
	ArrayList<LinkedBasedQ> queues;
	public runner(LinkedBasedQ q,ArrayList<LinkedBasedQ> queues){
		this.queues=queues;
		this.queues.add(q);
	}

	public void run(){
		synchronized (queues) {
			try {
				queues.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Waiting");
		}
	}
		}
@CrossOrigin
@SpringBootApplication
public class ProducerConsumerSimulatorBackEndApplication {
	MainClass main=new MainClass();
	public static void main(String[] args) {
		LinkedBasedQ q=new LinkedBasedQ();
		ArrayList<LinkedBasedQ> queues=new ArrayList<>();
		runner n=new runner(q,queues);
		Thread t=new Thread(n,"Runner");
		t.start();
		synchronized (queues) {
			queues.notify();
		}

	}

	@PostMapping("/produce")
	public String produce(){
		return main.produce();
	}

	@PostMapping("/createMachine")
	public void createMachine(){
		main.createMachine();
	}

	@PostMapping("/createQueue")
	public void createQueue(){
		main.createQueue();
	}
	@PostMapping("/addInQueue")
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
	@PostMapping("addOutQueue")
	public void addOutQueue(@RequestBody String indices){//Todo consistent with request sent from frontend (name is machineIndex and queueIndex)
		ObjectMapper mapper=new ObjectMapper();
		Map<String,Integer> indicesMap=null;
		try {
			indicesMap=(Map<String,Integer>)mapper.readValue(indices, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		main.addOutQueue(indicesMap.get("machineIndex"),indicesMap.get("queueIndex"));
	}
}
