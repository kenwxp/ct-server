
import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.WebSocketServerApplication;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.mq.MessageBody;
import com.cloudtimes.common.utils.JWTManager;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebSocketServerApplication.class)
public class AppSpringbootTest {

    @Autowired
    RocketMQTemplate rocketMQTemplate;
    @Autowired
    JWTManager jwtManager;

    //    @Test
    public void testMQ() {
        for (int i = 0; i < 100; i++) {
            MessageBody body = new MessageBody();
            body.setPayload("HELLOWORLD:" + Math.random());
            //  rocketMQTemplate.convertAndSend(RocketMQConstants.TOPIC_DEVICE, body);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Test
    public void testJSON() {
        AuthUser authUser = new AuthUser();
        authUser.setId("1315161");
    //    authUser.setChannelType("web");
        String s = JSONObject.toJSONString(authUser);
        System.out.println(s);
        AuthUser authUser1 = JSONObject.parseObject(s, AuthUser.class);
        System.out.println(authUser1);
    }

}
