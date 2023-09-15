package croplanet.admin.web.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public String userPage(Model model, HttpServletRequest request){

        HttpSession session = request.getSession(false);
        model.addAttribute("query", session.getId());
        return "user/user_page";
    }

    @PostMapping("/share")
    @ResponseBody
    public ResponseEntity share(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/like_top")
    @ResponseBody
    public ResponseEntity likeTop(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/like_bottom")
    @ResponseBody
    public ResponseEntity likeBottom(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/basket")
    public String basket(){
        return "user/reservation";
    }

    @PostMapping("/buy")
    @ResponseBody
    public ResponseEntity buy(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/post")
    @ResponseBody
    public ResponseEntity post(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/swipe")
    @ResponseBody
    public ResponseEntity swipe(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/scroll_bottom")
    @ResponseBody
    public ResponseEntity scrollBottom(){
        return new ResponseEntity(HttpStatus.OK);
    }

}
