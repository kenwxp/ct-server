
import com.cloudtimes.WebSocketServerApplication;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.mq.models.MessageBody;
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

    @Test
    public void testMQ() {
        for(int i =0;i<100;i++){
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

}
