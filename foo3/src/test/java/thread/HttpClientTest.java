package thread;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HttpClientTest {

    @Test
    public void testGet() {
        //百度天气的api
        //String url1 = "http://api.map.baidu.com/telematics/v3/weather?location=%E5%8C%97%E4%BA%AC&output=json&ak=W69oaDTCfuGwzNwmtVvgWfGH";
        String url1 = "http://localhost:8080/wechat/test/view2/你好世界";
        String result1 = HttpUtil.sendGet(url1);
        System.out.println(result1);
        //输出{"param":"你好世界"}
    }
    @Test
    public void testPost() throws UnsupportedEncodingException{
        String url = "http://localhost:8080/wechat/test/view";
        Map<String,String> map = new HashMap<String,String>();
        map.put("param1", "你好世界");
        map.put("param2", "哈哈");
        String result = HttpUtil.sendPost(url, map);
        System.out.println(result);
        //输出结果{"param1":"你好世界","param2":"哈哈"}

    }

    @Test
    public void testPost1() throws UnsupportedEncodingException{
        String url = "http://localhost:8080/wechat/test/view3";
        String result = HttpUtil.sendPost(url);
        System.out.println(result);
        //输出结果{"status":"success"}

    }

}