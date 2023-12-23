package org.raliev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;

@Component
public class MessageSender {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(String queueName, byte[] data) {
		jmsTemplate.send(queueName, session -> {
			BytesMessage bytesMessage = session.createBytesMessage();
			bytesMessage.writeBytes(data);
			return bytesMessage;
		});
	}
}
