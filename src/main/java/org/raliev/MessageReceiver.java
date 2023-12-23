package org.raliev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class MessageReceiver {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void receive(String queueName) throws IOException, JMSException {
		Message message = jmsTemplate.receive(queueName);
		BytesMessage bytesMessage = (BytesMessage) message;

		writeToFile(bytesMessage);
	}

	private void writeToFile(BytesMessage bytesMessage) throws IOException, JMSException {
		// copy data into a byte[] buffer
		int dataSize = (int) bytesMessage.getBodyLength();
		byte[] buffer = new byte[dataSize];
		bytesMessage.readBytes(buffer, dataSize);

		File outputFile = new File("/Users/renataliev/Desktop/292/folder/file");
		try (FileOutputStream fileOutput = new FileOutputStream(outputFile)) {
			fileOutput.write(buffer);
		}
	}
}
