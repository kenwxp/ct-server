import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import com.cloudtimes.partner.hik.service.impl.CtHikApiServiceImpl;
import com.cloudtimes.partner.wiegand.ICtWiegandApiService;
import com.cloudtimes.partner.wiegand.impl.CtWiegandApiService;
import org.junit.Test;

import java.util.Map;

public class TestWiegand {
    @Test
    public void test1() {
        ICtWiegandApiService service = new CtWiegandApiService();
        service.remoteOpenDoor(153204320);
    }

    @Test
    public void test2() {
        ICtWiegandApiService service = new CtWiegandApiService();
//        service.settingAccess(153204320,342312,false,"2023-01-30 12:47:00","2023-01-30 12:53:00");
        service.deleteAccess(153204320, 342312);
    }

    @Test
    public void test3() {
        ICtWiegandApiService service = new CtWiegandApiService();
        service.settingParams(153204320, 0, 5);
    }

    @Test
    public void test4() {
        ICtWiegandApiService service = new CtWiegandApiService();
        service.enablePassword(153204320, true);
    }

}
