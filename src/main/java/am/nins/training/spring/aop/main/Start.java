package am.nins.training.spring.aop.main;

import am.nins.training.spring.aop.objects.FileManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		FileManager fileUtil = (FileManager) context.getBean("fileManager");
		fileUtil.getExtensionList("c:\\Windows\\System32");
		fileUtil.getExtensionCount("c:\\Windows\\System32");
		fileUtil.getExtensionCount("c:\\Windows\\");
	}
}
