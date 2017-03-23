package spring4.third.lazyInitSample;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * すべてのbeanを遅延生成させる
 */
@Component
public class LazyInitBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        System.out.println("ComponentB以外は遅延生成に設定する");
        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            if (!"componentB".equals(beanName)) {
                beanFactory.getBeanDefinition(beanName).setLazyInit(true);
            }
        }
    }
}
