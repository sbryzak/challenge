package org.sample.team.orange;

import java.util.StringTokenizer;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;



public class BuzzWordFilter implements Processor {
	
	String buzzword;
	String inout;
	
	public BuzzWordFilter(String buzzword){
		this.buzzword = buzzword;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		
		final Message inMessage = exchange.getIn();
		final String body = inMessage.getBody(String.class);
		System.out.println("### BuzzWordFilter gets" + body);
		
		buzzword = (String) exchange.getIn().getHeader("buzzword");
		int count=0;		
		
		StringTokenizer tokenizer = new StringTokenizer(buzzword, " ");
		while(tokenizer.hasMoreElements()){
			String aToken = tokenizer.nextToken();
			if (buzzword.equalsIgnoreCase(aToken.trim())){
				System.out.println("HiT!");
				count++;
			}
		}
		
		String countAsString = String.valueOf(count);
		exchange.getOut().setHeader("count", countAsString );
		exchange.getOut().setHeader("url", exchange.getIn().getHeader("url"));
		exchange.getOut().setHeader("buzzword", exchange.getIn().getHeader("buzzword"));
		exchange.getOut().setHeader("email", exchange.getIn().getHeader("email"));
		
		
		System.out.println("### BuzzWord in:" + body + " ###");
	}
}


