import com.cloudtimes.partner.weixin.ICtWeixinApiService;
import com.cloudtimes.partner.weixin.impl.CtWeixinApiServiceImpl;
import org.junit.Test;

import java.util.Map;

public class TestWeixin {
    public void test1() {
        ICtWeixinApiService service = new CtWeixinApiServiceImpl();
        String token = service.getAccessToken();
        System.out.println(token);
    }

    public void test2() {
        ICtWeixinApiService service = new CtWeixinApiServiceImpl();
        Map<String, String> session = service.getUserSession("aaadsd");
        System.out.println(session);
    }

    public void test3() {
        ICtWeixinApiService service = new CtWeixinApiServiceImpl();
        Map<String, String> session = service.getUserPhoneInfo("aaadsd");
        System.out.println(session);
    }

}
