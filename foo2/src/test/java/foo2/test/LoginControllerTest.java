//package foo2.test;
//
//import org.junit.Test;
//import org.springframework.beans.BeansException;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//
//public class LoginControllerTest extends BaseControllerTest {
//
//	@Test
//	public void testLogin() {
//		try {
//
//			MockHttpServletRequest request = new MockHttpServletRequest();
//			MockHttpServletResponse response = new MockHttpServletResponse();
//			request.setMethod("POST");
//			request.addParameter("username", "aa");
//			request.addParameter("password", "bb");
//		} catch (BeansException e) {
//			e.printStackTrace();
//		}
//	}
//
//}