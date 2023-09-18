package croplanet.admin.web.common.config;

import croplanet.admin.web.common.interceptor.UserMethodInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final UserMethodInterceptor userMethodInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TestController 인터셉터를 등록합니다.
        registry.addInterceptor(userMethodInterceptor)
                .order(1)
                .addPathPatterns("/users", "/users/**");
    }
}
