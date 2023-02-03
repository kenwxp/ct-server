package com.cloudtimes.test;

import com.cloudtimes.AdminApplication;
import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.service.ICtUserService;
import com.cloudtimes.system.service.ISysConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class AppSpringbootTest {

    @Autowired
    private ICtUserService ctUserService;

    @Autowired
    private ISysConfigService configService;

    @Test
    public void testCtUser() {

      String key =  configService.selectConfigByKey("shouqianba_key");
        for (int i = 0; i < 10000; i++) {
            CtUser user = new CtUser();
//            user.setUserCode("U" + i);
            user.setNickName("我是" + i);
            ctUserService.insertCtUser(user);
        }

    }

}
