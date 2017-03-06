package web.content;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class MyContentInit {
	private static final Logger logger = Logger.getLogger(MyContentInit.class);

	private String name;

	public MyContentInit() {
		name = "张三00000999";
		logger.info("MyContentInit.......");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
