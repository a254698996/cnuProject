/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.core.CP_InitializingInterceptor.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年8月27日 下午4:26:59 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年8月27日    |    Administrator     |     Created 
 */
package foo3.core;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年8月27日 下午4:26:59 
 * @author Administrator  
 * @version V1.0                             
 */
public class CP_InitializingInterceptor extends HandlerInterceptorAdapter {
	Logger logger = Logger.getLogger(CP_InitializingInterceptor.class);

	/***
	 *   请求前到此方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		// log追加：用户名 - sessionID - IP - URL - 请求参数
		StringBuffer log = new StringBuffer();
		log.append(" - ").append(request.getSession().getId());
		log.append(" - ").append(request.getRemoteAddr());
		log.append(" - ").append(request.getServletPath());
		if (request.getQueryString() != null) {
			log.append(" - ").append(request.getQueryString()).append(" - ");
		} else {
			Map<String, String[]> parameters = request.getParameterMap();
			if (parameters.size() != 0) {
				log.append(" - [");
			}
			for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				String message = "";
				if (value.getClass().isArray()) {
					Object[] args = (Object[]) value;
					message = " " + key + "=" + Arrays.toString(args) + " ";
				} else {
					message = key + "=" + (String) value + " ";
				}
				log.append(message);
			}
			if (parameters.size() != 0) {
				log.append("]");
			}
		}
		logger.info(log.toString());
		
//		logger.info("---------------11111111111111111111111111---");

		return true;
	}
	
	/***
	 *   请求后到此方法  在此，在此有机会改变跳转地址 ModelAndVie 
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("_contextPath", request.getContextPath());
		String serverName = "http://" + request.getServerName();
		String serverPort = ":" + request.getServerPort();
		String httpPath = serverName + serverPort ;
		request.setAttribute("_serverPath", httpPath);
//		logger.info("---------------22222222222222222222---");
	}

	/***
	 *  返回处理(已经渲染了页面) 可以根据ex 是否为 null 判断是否发生了异常,进行日志处理
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
//		logger.info("---------------333333333333333333333---");
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
//		logger.info("---------------444444444444444444444---");
	}

}


