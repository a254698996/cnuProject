package web.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Session处理器
 * 
 * @author chenxin
 * @version [版本号, 2012-5-21]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SessionUtil {

	/**
	 * 从session中获取信息
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getAttribute(HttpServletRequest request, Serializable key) {

		HttpSession session = request.getSession(false);

		return session.getAttribute(key.toString());
	}

	/**
	 * 往session中存放信息
	 * 
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setAttribute(HttpServletRequest request, String key, Object value) {

		HttpSession session = request.getSession(true);
		session.setAttribute(key, value);
	}

}