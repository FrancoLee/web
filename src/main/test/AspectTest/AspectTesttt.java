package AspectTest;

import com.lx.animation.Aspect.AspectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by root on 18-7-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml","classpath*:spring-servlet.xml"})
public class AspectTesttt {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private AspectService aspectService;
    @Test
    public void test(){

    }
}
