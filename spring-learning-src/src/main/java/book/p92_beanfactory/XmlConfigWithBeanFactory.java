package book.p92_beanfactory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.net.URL;
import java.net.URLClassLoader;


public class XmlConfigWithBeanFactory {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory =
            new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader =
            new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource(
            "spring/beanfactory_92/xml-bean-factory-config.xml"));
        Oracle oracle = (Oracle) factory.getBean("oracle");
        System.out.println(oracle.defineMeaningOfLife());
    }
}
