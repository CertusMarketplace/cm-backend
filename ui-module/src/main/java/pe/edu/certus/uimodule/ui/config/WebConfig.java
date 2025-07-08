package pe.edu.certus.uimodule.ui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
<<<<<<< Updated upstream
    public void addResourceHandlers( ResourceHandlerRegistry registry ) {

        registry.addResourceHandler( "/css/**" )
                .addResourceLocations( "classpath:/frontend-config/css/" );

        registry.addResourceHandler( "/scripts/**" )
                .addResourceLocations( "classpath:/static/scripts/" );

        registry.addResourceHandler( "/img/**" )
                .addResourceLocations( "classpath:/static/img/" );

        registry.addResourceHandler( "/video/**" )
                .addResourceLocations( "classpath:/static/video/" );
=======
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/frontend-config/css/");

        registry.addResourceHandler("/scripts/**")
                .addResourceLocations("classpath:/static/scripts/");

        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");

        registry.addResourceHandler("/video/**")
                .addResourceLocations("classpath:/static/video/");
>>>>>>> Stashed changes
    }
}