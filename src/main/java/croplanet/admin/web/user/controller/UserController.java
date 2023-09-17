package croplanet.admin.web.user.controller;

import croplanet.admin.domain.entity.Reservation;
import croplanet.admin.domain.repository.ReservationRepository;
import croplanet.admin.web.user.dto.KakaoDTO;
import croplanet.admin.web.user.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final KakaoService kakaoService;
    private final ReservationRepository reservationRepository;

    @GetMapping
    public String userPage(Model model, HttpServletRequest request){

        //공유 시 전달될 세션 아이디 추가
        HttpSession session = request.getSession(false);
        model.addAttribute("query", session.getId());

        //어드민 페이지 에서 작성한 마케팅 문구 추가
        String comment = "qweqwe\nqweqwe\nqweqwe\nqweqwe";
        model.addAttribute("comment", comment);

        return "user/user_page";
    }

    @GetMapping("/login")
    public String login(){
        return "redirect:"+kakaoService.getKakaoLogin();
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
    public String basket(HttpServletRequest request, Model model) throws Exception {

        //카카오 정보 추가
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"));
        model.addAttribute("kakaoInfo", kakaoInfo);
        log.debug("kakaoInfo={}", kakaoInfo);

        //사전예약 순위 추가
        Reservation reservation;
        Optional optional = reservationRepository.findByKakaoId(kakaoInfo.getId());
        if(optional.isPresent()){
            reservation = (Reservation) optional.get();
        }else {
            reservation = new Reservation();
            reservation.setKakaoId(kakaoInfo.getId());
            reservationRepository.save(reservation);
        }

        model.addAttribute("reservation", reservation);

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
