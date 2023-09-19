package kr.co.enders.engine.util;

import org.springframework.context.ApplicationContext;

public class BeanUtils {
	
	public static Object getBean(String beanName) {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		return applicationContext.getBean(beanName);
	}
}
