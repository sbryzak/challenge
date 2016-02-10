package org.sample.team.orange;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;



public class BuzzWordFilter implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		final Message inMessage = exchange.getIn();
		final String body = inMessage.getBody(String.class);
		System.out.println("### BuzzWord in:" + body + " ###");
	}
}


