package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// app이 로드될 때(deploy) 감시하는 자
public class ContextLoaderListener implements ServletContextListener {
	// deploy 되는 것을 감지했다가 실행
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("ContextLoaderListener contextInitialized() 실행");
		String key1 = event.getServletContext().getInitParameter("key1");
		System.out.println("key1: " + key1);
	}

	// undeploy 되는 것을 감지했다가 실행
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("ContextLoaderListener contextDestroyed() 실행");
	}

}
