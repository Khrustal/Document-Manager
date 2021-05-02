package org.example.config;

import org.example.documentmanagerdao.DocTypeDao;
import org.example.documentmanagerdao.DocTypeDaoImpl;
import org.example.documentmanagerservices.DocTypeService;
import org.example.documentmanagerservices.DocTypeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.*")
public class AppConfigSpring {
    @Bean
    public DocTypeDao getDocTypeDao() {
        return new DocTypeDaoImpl();
    }

    @Bean(name = "docTypeService")
    public DocTypeService getDocTypeService() {
        return new DocTypeServiceImpl();
    }
}
