package db.project.movie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    String PermittedPath = "file:///" +System.getProperty("user.dir") + "/src/main/resources/static/";

//    @Value("${uploadPath}")
//    String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**") // PermittedPath 경로들/+a
                .addResourceLocations(PermittedPath); // 로컬에 저장된 파일을 읽어 올 root 경로 설정
//                .addResourceLocations(path); // 로컬에 저장된 파일을 읽어 올 root 경로 설정
    }

}