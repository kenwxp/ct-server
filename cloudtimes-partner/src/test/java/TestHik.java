import com.cloudtimes.partner.hik.domain.DeviceInfoData;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import com.cloudtimes.partner.hik.service.impl.CtHikApiServiceImpl;
import org.junit.Test;

import java.util.Map;

public class TestHik {
    public void test1() {
        ICtHikApiService service = new CtHikApiServiceImpl();
        DeviceInfoData result = service.getDeviceInfo("J58092487");
        System.out.println(result);
    }

    public void test2() {
//        Map<String, String> config = new PartnerConfig().getWeiXinConfig();
//        System.out.println(config.toString());
    }

    public void test3() {
        ICtHikApiService service = new CtHikApiServiceImpl();
        DeviceInfoData result = service.getDeviceInfo("G28019093");
        System.out.println(result);
    }

    public void test4() {
        ICtHikApiService service = new CtHikApiServiceImpl();
        Map result = service.getLiveAddress("J58092487", "1", "2");
        System.out.println(result.get("url"));
    }

    public void test5() {
        ICtHikApiService service = new CtHikApiServiceImpl();
        String result = service.setDeviceEncrypt("J58092487", "CVBSQS", false);
        System.out.println(result);
    }

    public void test6() {
        ICtHikApiService service = new CtHikApiServiceImpl();
        String result = service.getDeviceCapture("J58092487");
        System.out.println(result);
    }
}
