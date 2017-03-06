package web.content;

import org.apache.log4j.Logger;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationObjectSupport extends ApplicationObjectSupport {
	private static final Logger logger = Logger.getLogger(MyApplicationObjectSupport.class);

	public MyApplicationObjectSupport() {
		logger.info("MyApplicationObjectSupport....");
	}
}
