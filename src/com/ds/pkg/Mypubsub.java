package com.ds.pkg;

import java.util.ArrayList;
import java.util.HashMap;

public class Mypubsub {
	
	public static HashMap<String, ArrayList<String>> topicSubscribers = new HashMap<String, ArrayList<String>>();
	
	

	public static HashMap<String, String> subsTopics = new HashMap<String, String>();
	
	
	public static String Printtopicmsg(String topic,String news){
		
		ArrayList<String> list = new ArrayList<String>();
		list = topicSubscribers.get(topic);
		String msg = list.toString() +" have recieved the message : " + news + " on topic : " + topic;
		return msg;
		
	} 
	
	public static String addSubscriber(String subscriberName , String subscriptionList){
		System.out.println( subscriberName + " **** " + subscriptionList);
		String[] subscList = subscriptionList.split(", ");
		
		for(int i=0;i<subscList.length;i++){
			ArrayList<String> list  = new ArrayList<>();
			if(topicSubscribers.containsKey(subscList[i]))
			list = topicSubscribers.get(subscList[i]);
			list.add(subscriberName);
			topicSubscribers.put(subscList[i], list);
		}
		subsTopics.put(subscriberName, subscriptionList);
		System.out.println();
		String msg  = subscriberName + " has been added succesfully with subscriptions list : " + subscriptionList;
		
		for (String name: topicSubscribers.keySet()){

            String key =name.toString();
            String value = topicSubscribers.get(name).toString();  
            System.out.println(key + " " + value);  


}
		
		for (String name: subsTopics.keySet()){

            String key =name.toString();
            String value = subsTopics.get(name).toString();  
            System.out.println(key + " " + value);  


}
		
		return msg;
		
	}
	
	public static String updateSubscriber(String subscriberName , String subscriptionList){
		
		String[] subscList = subscriptionList.split(", ");
		
		ArrayList<String> arrListsports  = new ArrayList<>();
		arrListsports = topicSubscribers.get("Sports");
		if(arrListsports!=null)
		if(arrListsports.contains(subscriberName))
			arrListsports.remove(subscriberName);
		
		if(arrListsports==null)
			arrListsports = new ArrayList<>();
		topicSubscribers.put("Sports", arrListsports);
		
		ArrayList<String> arrListpolitics  = new ArrayList<>();
		arrListpolitics = topicSubscribers.get("Politics");
		if(arrListpolitics!=null)
		if(arrListpolitics.contains(subscriberName))
			arrListpolitics.remove(subscriberName);
		if(arrListpolitics==null)
			arrListpolitics = new ArrayList<>();
		topicSubscribers.put("Politics", arrListpolitics);
		
		ArrayList<String> arrListMusic  = new ArrayList<>();
		arrListMusic = topicSubscribers.get("Music");
		if(arrListMusic!=null)
		if(arrListMusic.contains(subscriberName))
			arrListMusic.remove(subscriberName);
		if(arrListMusic==null)
			arrListMusic = 	new ArrayList<>();
		topicSubscribers.put("Music", arrListMusic);
		
		for(int i=0;i<subscList.length;i++){
			ArrayList<String> list  = new ArrayList<>();
			if(topicSubscribers.containsKey(subscList[i]))
			list = topicSubscribers.get(subscList[i]);
			list.add(subscriberName);
			topicSubscribers.put(subscList[i], list);
		}
		
		subsTopics.remove(subscriberName);
		subsTopics.put(subscriberName, subscriptionList);
		
		String msg  = subscriberName + " has been updated succesfully with subscriptions list : " + subscriptionList;
		
		for (String name: topicSubscribers.keySet()){

            String key =name.toString();
            String value = topicSubscribers.get(name).toString();  
            System.out.println(key + " " + value);  


}
		
		for (String name: subsTopics.keySet()){

            String key =name.toString();
            String value = subsTopics.get(name).toString();  
            System.out.println(key + " " + value);  


}
		
		return msg;
		
	}
	
	
	
	public static void topicList(){
		
		
		
	}
	
	public static void main(String[] args) {
		

	}

}
