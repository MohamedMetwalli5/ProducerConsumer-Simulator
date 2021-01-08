package eg.edu.alexu.csd.oop.mail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class ProducerConsumerSimulatorBackEndApplication {


	public static void main(String args[]){

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