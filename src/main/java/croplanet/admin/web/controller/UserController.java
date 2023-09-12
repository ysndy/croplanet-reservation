package croplanet.admin.web.controller;

import croplanet.admin.domain.Log;
import croplanet.admin.domain.LogRepository;
import croplanet.admin.web.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController implements HandlerInterceptor {

    private final LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 컨트롤러 메소드 실행 전에 실행되는 로직을 여기에 작성하세요.
        log.info("request = {}", request.getRequestURI());
        Log log = new Log(request.getRequestURI().replace("/users/", ""));
        logService.save(log);
        return true; // true를 반환하면 컨트롤러 메소드가 실행됩니다.
    }

    @GetMapping
    public String userPage(){
        return "user/user_page";
    }

    @PostMapping("/button1")
    public String button1(){
        return "redirect:/users";
    }

    @PostMapping("/button2")
    public String button2(){
        return "redirect:/users";
    }

    @PostMapping("/button3")
    public String button3(){
        return "redirect:/users";
    }

    @PostMapping("/button4")
    public String button4(){
        return "redirect:/users";
    }
}
