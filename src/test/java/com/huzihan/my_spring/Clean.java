package com.huzihan.my_spring;

import com.huzihan.my_spring.aop.*;
import com.huzihan.my_spring.bean.BeanDefinition;
import com.huzihan.my_spring.context.ApplicationContext;
import com.huzihan.my_spring.context.ClassPathXmlApplicationContext;
import com.huzihan.my_spring.factory.AbstractBeanFactory;
import com.huzihan.my_spring.factory.AutowireCapableBeanFactory;
import com.huzihan.my_spring.io.ResourceLoader;
import com.huzihan.my_spring.reader.XmlBeanDefinitionReader;
import com.huzihan.my_spring.service.HelloWorldService;
import com.huzihan.my_spring.service.HelloWorldServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class Clean {

    @Test
    public void test()throws Exception{
        // 1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinition("tinyioc.xml");

        // 2.初始化BeanFactory并注册bean
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }

    @Test
    public void test2() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        // OutputService outputService = (OutputService) applicationContext.getBean("outputService");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");

        // Assert.assertNotNull(helloWorldService);
        helloWorldService.helloWorld();
    }

    @Test
    public void test3() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService)applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();

        // --------- helloWorldService with AOP
        // 1. 设置被代理对象(Joinpoint)
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargeSource targeSource = new TargeSource(helloWorldService, HelloWorldServiceImpl.class,
                HelloWorldService.class);
        advisedSupport.setTargetSource(targeSource);

        //2.设置拦截器(Advice)
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);

        //3.创建代理(Proxy)
        JdkDynmicAopProxy jdkDynmicAopProxy = new JdkDynmicAopProxy(advisedSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService)jdkDynmicAopProxy.getProxy();

        //4.基于AOP的调用
        helloWorldServiceProxy.helloWorld();
    }

    @Test
    public void test4(){
        String expression = "execution(* com.huzihan.my_spring.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldService.class);
        System.out.println(matches);
    }

    @Test
    public void test5() throws NoSuchMethodException {
        String expression = "execution(* com.huzihan.my_spring.service.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher()
                .matches(HelloWorldServiceImpl.class.getDeclaredMethod("helloWorld"), HelloWorldServiceImpl.class);
        Assert.assertTrue(matches);
        System.out.println(matches);
    }
}
