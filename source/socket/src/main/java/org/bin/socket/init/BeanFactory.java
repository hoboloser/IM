package org.bin.socket.init;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanFactory implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		if (BeanFactory.applicationContext == null) {
			BeanFactory.applicationContext = arg0;
		}
		System.out.println("-------------------------------------------");
		System.out.println("BeanFactory.applicationContext == "
				+ BeanFactory.applicationContext);
		System.out.println("-------------------------------------------");

	}
	
	/***
     * 根据一个bean的id获取配置文件中相应的bean
     */
    public static Object getBean(String beanId) throws BeansException {
        if (applicationContext.containsBean(beanId)) {
            return applicationContext.getBean(beanId);
        }
        return null;
    }

    /***
     * 根据一个bean的类型获取配置文件中相应的bean
     */
    public static <T> T getBeanByClass(Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(requiredType);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static ApplicationContext getApplicationContext() {
        return BeanFactory.applicationContext;
    }
    
}
