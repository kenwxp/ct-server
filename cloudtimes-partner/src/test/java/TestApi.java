import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import com.cloudtimes.partner.hik.service.impl.CtHikApiServiceImpl;
import org.junit.Test;

import java.util.Map;

public class TestApi {
    @Test
    public void test1() {
        ICtHikApiService service = new CtHikApiServiceImpl();
        String result = service.getDeviceInfo("");
        System.out.println(result);
    }


    @Test
    public void test2() {
        Map<String, String> config = new PartnerConfig().getWeiXinConfig();
        System.out.println(config.toString());
    }

    @Test
    public void test3() {
        ICtHikApiService service = new CtHikApiServiceImpl();
        String result = service.getDeviceInfo("G28019093");
        System.out.println(result);
    }

}
