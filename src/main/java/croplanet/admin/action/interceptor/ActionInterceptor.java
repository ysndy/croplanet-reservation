package croplanet.admin.action.interceptor;

import croplanet.admin.action.service.ActionService;
import croplanet.admin.common.util.Constant;
import croplanet.admin.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class ActionInterceptor implements HandlerInterceptor {

    private final ActionService actionService;

    //사용자 행동을 DB에 저장
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("request = {}", request.getRequestURI());

        String action;

        if(request.getRequestURI().equals("/users")){
            action = request.getRequestURI().replace("/users", "first_open");
        }else {
            action = request.getRequestURI().replace("/users/", "");
        }

        String query = request.getParameter("query");

        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        log.info("user = {}", user);
        actionService.save(user, action, query);

        return true;
    }


}
