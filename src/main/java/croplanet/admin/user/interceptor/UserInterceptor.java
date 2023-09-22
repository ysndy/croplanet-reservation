package croplanet.admin.user.interceptor;

import croplanet.admin.common.util.Constant;
import croplanet.admin.user.domain.User;
import croplanet.admin.user.repository.UserRepository;
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
public class UserInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if(session==null){

            session = request.getSession(true);
            session.setMaxInactiveInterval(-1);
            User user = new User();
            user.setSessionId(session.getId());
            userRepository.save(user);

            session.setAttribute(Constant.SESSION_USER, user);

            log.info("user 신규 생성 = {}", session.getAttribute(Constant.SESSION_USER));

        }

        return true;
    }
}
