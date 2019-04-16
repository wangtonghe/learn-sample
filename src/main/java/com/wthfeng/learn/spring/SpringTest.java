package com.wthfeng.learn.spring;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @author wangtonghe
 * @since 2019/4/3 11:32
 */
public class SpringTest {


    @Test
    public void loadSpring() {

        // ① 定位资源（bean配置文件）
        ClassPathResource resource = new ClassPathResource("spring/beans.xml");

        // ② 创建bean容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        // ③ 创建一个资源读取器（传入了上步的bean容器）
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        // ④ 解析bean配置文件
        reader.loadBeanDefinitions(resource);

        // spring容器已创建，使用bean容器操作bean
        WorldDao worldDao = (WorldDao) factory.getBean("worldDao");
        System.out.println(worldDao.getUser2());
    }
}
