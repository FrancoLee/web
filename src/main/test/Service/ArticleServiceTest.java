package Service;

import com.lx.animation.config.BeanClass;
import com.lx.animation.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by root on 18-7-22.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleServiceTest {


    @Test
    public void test() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        BeanClass beanClass = (BeanClass) annotationConfigApplicationContext.getBean("beanClass");
        System.out.println(beanClass.div(1, 0));
    }
}
