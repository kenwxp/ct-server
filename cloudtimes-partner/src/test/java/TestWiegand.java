import com.cloudtimes.partner.wiegand.ICtWiegandApiService;
import com.cloudtimes.partner.wiegand.impl.CtWiegandApiServiceImpl;
import org.junit.Test;

public class TestWiegand {
    public void test1() {
        ICtWiegandApiService service = new CtWiegandApiServiceImpl();
        service.remoteOpenDoor("153204320");
    }

    public void test2() {
        ICtWiegandApiService service = new CtWiegandApiServiceImpl();
//        service.settingAccess(153204320,342312,false,"2023-01-30 12:47:00","2023-01-30 12:53:00");
        service.deleteAccess("153204320", 342312);
    }

    public void test3() {
        ICtWiegandApiService service = new CtWiegandApiServiceImpl();
        service.settingParams("153204320", 0, 5);
    }

    public void test4() {
        ICtWiegandApiService service = new CtWiegandApiServiceImpl();
        service.enablePassword("153204320", true);
    }

}
