package croplanet.admin.common.config;

import croplanet.admin.action.interceptor.ActionInterceptor;
import croplanet.admin.user.interceptor.UserInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final ActionInterceptor actionInterceptor;
    private final UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(userInterceptor)
                .order(1)
                .addPathPatterns("/users", "/users/**");

        registry.addInterceptor(actionInterceptor)
                .order(2)
                .addPathPatterns("/users", "/users/**")
                .excludePathPatterns("/users/login");
    }
}
