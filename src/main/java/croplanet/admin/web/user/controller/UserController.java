package croplanet.admin.web.user.controller;

import croplanet.admin.domain.entity.Reservation;
import croplanet.admin.domain.entity.survey.Survey;
import croplanet.admin.domain.entity.survey.SurveyResponse;
import croplanet.admin.domain.repository.survey.SurveyRepository;
import croplanet.admin.domain.repository.survey.SurveyResponseRepository;
import croplanet.admin.web.common.dto.SurveyResponseDto;
import croplanet.admin.web.common.util.FileManager;
import croplanet.admin.web.user.dto.KakaoDTO;
import croplanet.admin.web.user.service.KakaoService;
import croplanet.admin.web.user.service.UserMethodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final KakaoService kakaoService;
    private final UserMethodService userMethodService;
    private final FileManager fileManager;
    private final SurveyRepository surveyRepository;
    private final SurveyResponseRepository surveyResponseRepository;

    @GetMapping
    public String userPage(Model model, HttpServletRequest request){

        //공유 시 전달될 세션 아이디 추가
        HttpSession session = request.getSession(false);
        model.addAttribute("query", session.getId());

        //어드민 페이지 에서 작성한 마케팅 문구 추가
        StringBuilder fileContent = fileManager.getMarketingComment();
        model.addAttribute("comment", fileContent.toString()); // 모델에 파일 내용 추가

        //저장된 설문조사를 모델에 추가
        List<Survey> surveys = surveyRepository.findAll();

        model.addAttribute("surveys", surveys);

        SurveyResponseDto surveyResponseDto = new SurveyResponseDto();
        for(int i=0; i<surveys.size(); i++){
            surveyResponseDto.getResponses().put(surveys.get(i).getId(), new LinkedList<>());
        }
        model.addAttribute("surveyResponseDto", new SurveyResponseDto());

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


    //예약 관련
    @GetMapping("/basket")
    public String basket(HttpServletRequest request, Model model) throws Exception {

        //카카오 정보 추가
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"));
        model.addAttribute("kakaoInfo", kakaoInfo);
        log.debug("kakaoInfo={}", kakaoInfo);

        //사전예약 순위 추가
        Reservation reservation = userMethodService.addReservation(kakaoInfo);
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

    @PostMapping("/survey")
    public String survey(SurveyResponseDto surveyResultsDto){

        log.info("surveyDto = {}", surveyResultsDto);

        Map<Long, List<String>> responses = surveyResultsDto.getResponses();

        for (Long surveyId : responses.keySet()) {

            Survey survey = surveyRepository.findById(surveyId).get();
            List<String> answerStrings = responses.get(surveyId);

            for (String answerString : answerStrings) {

                SurveyResponse surveyResponse = new SurveyResponse();
                surveyResponse.setSurvey(survey);
                surveyResponse.setAnswer(answerString);

                surveyResponseRepository.save(surveyResponse);

            }

        }

        return "redirect:/users";

    }

}
