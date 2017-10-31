package springaop.dynaProxy;

import java.lang.reflect.Method;

public interface ILogger {
	public void start(Method method);

	public void end(Method method);
}