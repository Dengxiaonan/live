package requireproject;

import com.didispace.swagger.EnableSwagger2Doc;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author ldy
 * @Descriptions 需求启动类
 * @Date 2020/11/25 10:25
 * @Version: V1.0
 **/
@EnableSwagger2Doc
@Log4j2
@SpringBootApplication
public class RequireprojectApplication extends WebMvcConfigurerAdapter {

//    @Autowired
//    private CheckLoginIntercepter globalInterceptor;

    public static void main(String[] args) {
//        SpringApplication.run(RequireprojectApplication.class, args);
        Environment env = SpringApplication.run(RequireprojectApplication.class, args).getEnvironment();
//        log.info("\n----------------------------------------------------------\n\t" +
//                        "服务 '{}' 启动完成! \n\t" +
//                        "环境(s): \t{}\n----------------------------------------------------------",
//                env.getProperty("spring.application.name"),
//                env.getActiveProfiles());
    }

    /**
     * @Author ldy
     * @Description MD5 明文密文对比
     * @Date 2020/11/25 17:05
     **/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    CheckLoginIntercepter localInterceptor() {
//        return new CheckLoginIntercepter();
//    }
//
//
//   @Override
//   public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localInterceptor()).addPathPatterns("/require/**")
//        .excludePathPatterns("/sysUser/**");
////       registry.addInterceptor(globalInterceptor);
//        super.addInterceptors(registry);
//    }
}
