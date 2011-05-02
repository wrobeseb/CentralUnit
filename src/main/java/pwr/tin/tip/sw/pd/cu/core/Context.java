package pwr.tin.tip.sw.pd.cu.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Context {
	private static Context _instance;
	
	private ApplicationContext _appliactionContext;

	private Context() {}
	
	public static Context getInstance() {
		if (_instance == null) {
			_instance = new Context();
		}
		return _instance;
	}
	
	public static void initContext() {
		Context.getInstance().setContext(new ClassPathXmlApplicationContext("classpath:applicationContext.xml"));
	}
	
	public ApplicationContext getContext() {
		return _appliactionContext;
	}
	
	public void setContext(ApplicationContext applicationContext) {
		_appliactionContext = applicationContext;
	}
}
