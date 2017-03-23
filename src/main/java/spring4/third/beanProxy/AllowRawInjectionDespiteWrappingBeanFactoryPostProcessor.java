package spring4.third.beanProxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class AllowRawInjectionDespiteWrappingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        System.out.println("手順1");
        AbstractAutowireCapableBeanFactory f = (AbstractAutowireCapableBeanFactory) beanFactory;

        /**
         * この設定をしないとbeanに対してproxy生成はできない。
         */
        f.setAllowRawInjectionDespiteWrapping(true);
        beanFactory = (ConfigurableListableBeanFactory)f;
    }
}
