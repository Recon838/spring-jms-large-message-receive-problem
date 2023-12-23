package org.raliev;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;

@ComponentScan
public class Main {

	private static final String QUEUE_NAME = "TEST_QUEUE";

	public static void main(String... args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

		File file = ResourceUtils.getFile("classpath:large-file.jar");
//		File file = ResourceUtils.getFile("classpath:file.jar");

		byte[] data = Files.readAllBytes(file.toPath());

		MessageSender sender = context.getBean(MessageSender.class);
		sender.send(QUEUE_NAME, data);

		MessageReceiver receiver = context.getBean(MessageReceiver.class);
		receiver.receive(QUEUE_NAME);
	}
}