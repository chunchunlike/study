import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xi.dubbostudy.service.TestService;

import java.io.IOException;


public class Consumer {
    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-consumer-beans.xml"});
        context.start();

        TestService testService = (TestService) context.getBean("testService");
        System.out.println(testService.hello("xi"));

    }
}
