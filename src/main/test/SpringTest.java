import com.lx.animation.config.*;
import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringValueResolver;

import java.util.Collection;
import java.util.EventObject;
import java.util.stream.Stream;

public class SpringTest {
    @SuppressWarnings("resource")
    @Test
    public void test() {

//        System.out.println(properties.toString());
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        MyEventMulticaster myEventMulticaster= (MyEventMulticaster) annotationConfigApplicationContext.getBean("applicationEventMulticaster");
        MyListener myListener=new MyListener();
        myEventMulticaster.addApplicationListener(myListener);
        Collection<ApplicationListener<?>> applicationListeners=myEventMulticaster.getAppListers();
//        applicationEventMulticaster.removeApplicationListenerBean("MyListener");
        System.out.println("sqwe");
        applicationListeners.forEach(a-> System.out.println(a.toString()));
//

        //annotationConfigApplicationContext.getApplicationListeners().forEach(a-> System.out.println(a.toString()));
        //   (annotationConfigApplicationContext.getBeanFactory()).ignoreDependencyInterface(MyInterface.class);
        //        SystemProper systemProper= (SystemProper) annotationConfigApplicationContext.getBean("systemProper");
//        systemProper.print();
        //        System.out.println((annotationConfigApplicationContext.getBean("myProperties")).toString());
//        Stream.of(annotationConfigApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
//        //        AddressUtil addressUtil=(AddressUtil) annotationConfigApplicationContext.getBean("AddressUtil");
////        System.out.println(addressUtil.toString());
//
        // MyFactoryBean  myFactoryBean= (MyFactoryBean) annotationConfigApplicationContext.getBean("&myFactoryBean");
//
//        annotationConfigApplicationContext.publishEvent(new ApplicationEvent(new String("sss")) {
//        });
        //    UserTestService userTestService = (UserTestService) annotationConfigApplicationContext.getBean("userTestService");
        //    userTestService.txTest();
//        StringValueResolver stringValueResolver = ((BeanClass) annotationConfigApplicationContext.getBean("beanClass")).getEmbeddedValueResolverAware();
//
        //    Stream.of(annotationConfigApplicationContext.getBeanDefinitionNames()).forEach((s) -> System.out.println(s));
        //   MyInterface myInterface= (MyInterface) annotationConfigApplicationContext.getBean("beanClass");


//        beanClass.div(1, 3);
//        System.out.println(stringValueResolver.resolveStringValue("${env:APP_HOME}"));
//        ConfigurableEnvironment environment = annotationConfigApplicationContext.getEnvironment();

        // environment.getSystemEnvironment().forEach((k,v)-> System.out.println(k+" "+v));

    }
}
