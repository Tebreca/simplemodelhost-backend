package com.tebreca.simplemodelhost;

import com.tebreca.simplemodelhost.pojo.Model;
import com.tebreca.simplemodelhost.pojo.Table;
import com.tebreca.simplemodelhost.ui.TeacherUI;
import com.tebreca.simplemodelhost.util.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

@SpringBootApplication
public class SimplemodelhostApplication {

    public static final Logger logger = LoggerFactory.getLogger(SimplemodelhostApplication.class);
    public static final SystemController SYSTEM_CONTROLLER = new SystemController();
    private TeacherUI ui;


    public SimplemodelhostApplication() throws URISyntaxException, IOException {
        FileHelper.initIp();
        File modelFolder = new File("./models/");
        if (!modelFolder.exists()) {
            if (!modelFolder.mkdir()) {
                logger.error("Could not Create models folder, make sure to start the application with the right privileges!");
                System.exit(-1);
            }
        }
        Model[] models = FileHelper.scan(modelFolder);
        logger.debug(Arrays.toString(models));
        ModelManager.INSTANCE.add(models);
        ui = new TeacherUI(TableManager.getAll(), this);
        ui.show();
    }

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(SimplemodelhostApplication.class);

        builder.headless(false);

        ConfigurableApplicationContext context = builder.run(args);
    }

    public void refresh() {
        ui.hide();
        File modelFolder = new File("./models/");
        Model[] models = FileHelper.scan(modelFolder);
        ModelManager.INSTANCE.clear();
        ModelManager.INSTANCE.add(models);
        ui = new TeacherUI(TableManager.getAll(), this);
        ui.show();
    }


    @Configuration
    @EnableWebMvc
    public static class MvCConfig implements WebMvcConfigurer {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry
                    .addResourceHandler("/models/**")
                    .addResourceLocations("file:./models/");
        }

    }

    @Bean
    public static SystemController getSystemController() {
        return SYSTEM_CONTROLLER;
    }


}
