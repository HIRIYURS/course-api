package io.javabrains.springbootstarter.topic;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}

	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable String id) {
		return topicService.getTopic(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public void addTopic(@RequestBody Topic topic) {
		topicService.createTopic(topic);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		topicService.updateTopic(id, topic);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	public void updateTopic(@PathVariable String id) {
		topicService.deleteTopic(id);
	}

	// For A2I testing, creating an endpoint to accept POST requests
	@RequestMapping(method=RequestMethod.POST, value="/a2i")
	public String a2iStoreTxns(@RequestBody String txns) {
		
		System.out.println("Got Store Transactions: " + txns);
		
		JSONArray jsonArr = new JSONArray(txns);
		System.out.println("Numer of transactions: " + jsonArr.length());
		
		for (int i = 0; i < jsonArr.length(); i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			System.out.println("==========================");
			System.out.println(jsonObj);
		}
		System.out.println("==========================");

		return "OK";
	}
}
