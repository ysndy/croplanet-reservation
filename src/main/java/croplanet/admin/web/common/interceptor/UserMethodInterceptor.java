package croplanet.admin.web.common.interceptor;

import croplanet.admin.web.user.service.UserMethodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserMethodInterceptor implements HandlerInterceptor {

    private final UserMethodService userMethodService;

    //사용자 행동을 DB에 저장
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("request = {}", request.getRequestURI());
        HttpSession session = request.getSession(true);

        String action;

        if(request.getRequestURI().equals("/users")){
            action = request.getRequestURI().replace("/users", "first_open");
        }else {
            action = request.getRequestURI().replace("/users/", "");
        }

        String query = request.getParameter("query");

        userMethodService.save(session.getId(), action, query);
        return true;
    }


}
