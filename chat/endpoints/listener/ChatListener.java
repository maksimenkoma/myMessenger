package by.it_academy.jd2.m_jd2_88_22.chat.endpoints.listener;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.HibernateDBInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ChatListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("by.it_academy.jd2.m_jd2_88_22.chat");
        HibernateDBInitializer bean = context.getBean(HibernateDBInitializer.class);
        bean.getManager().getEntityManagerFactory().close();



    }
}
