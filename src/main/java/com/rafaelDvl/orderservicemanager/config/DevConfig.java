package com.rafaelDvl.orderservicemanager.config;

import com.rafaelDvl.orderservicemanager.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("dev")
public class DevConfig {
    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String dd1;

    @Bean
    public boolean isntanciaDB(){
        if(dd1.equals("create")){
            this.dbService.instaciaDB();
        }
        return false;
    }
}
