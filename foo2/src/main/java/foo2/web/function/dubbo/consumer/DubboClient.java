package foo2.web.function.dubbo.consumer;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.tarzan.message.MessageSender;

import com.alibaba.dubbo.config.annotation.Reference;

@Component
public class DubboClient {

	@Reference(lazy = true)
	@Lazy
	private MessageSender messageSender;

	public void sendMessage(String message) {
		messageSender.send(message);
	}
}
