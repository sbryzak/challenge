package org.sample.team.orange;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.sample.team.orange.model.UserParams;
import org.sample.team.orange.model.BuzzWord;


public class FakeUserParamsFactory implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		/**
		List <UserParams> ret = new ArrayList<UserParams>();
		//final Message inMessage = exchange.getIn();

		
		UserParams up1 = new UserParams();
		
		List<BuzzWord> bwl1 = new ArrayList<BuzzWord>();
		BuzzWord bw1 = new BuzzWord();
		BuzzWord bw2 = new BuzzWord();
		bwl1.add(bw1);
		bwl1.add(bw2);
		
		bw1.setWord("Spinach");
		bw1.setInOut("Out");
		bw1.setWord("Pizza");
		bw1.setInOut("In");
		
		List<String> urls1 = new ArrayList<String>();
		urls1.add("http://localhost:8080/BrnoChallenge/SimpleServlet1");
		urls1.add("http://localhost:8080/BrnoChallenge/SimpleServlet2");
		
		up1.setBuzzWords(bwl1);
		up1.setEmail("rwagner@redhat.com");
		up1.setUrls(urls1);
		
		ret.add(up1);
		**/
		
		
		exchange.getOut().setHeader("url", "http://localhost:8080/BrnoChallenge/SimpleServlet1");
		exchange.getOut().setHeader("buzzword", "Spinach");
		exchange.getOut().setHeader("inout", "In");
		exchange.getOut().setHeader("email", "shadowman@redhat.com");
		
		
		//exchange.getOut().setBody(ret);
	}
	
}
