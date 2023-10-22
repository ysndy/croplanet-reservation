package croplanet.admin.admin.controller;

import croplanet.admin.reservation.domain.Reservation;
import croplanet.admin.action.domain.Action;
import croplanet.admin.reservation.repository.ReservationQueryRepository;
import croplanet.admin.reservation.repository.ReservationSearchCond;
import croplanet.admin.survey.domain.Survey;
import croplanet.admin.survey.domain.SurveyResponse;
import croplanet.admin.reservation.repository.ReservationRepository;
import croplanet.admin.survey.repository.SurveyRepository;
import croplanet.admin.action.repository.ActionRepository;
import croplanet.admin.survey.repository.SurveyResponseRepository;
import croplanet.admin.admin.dto.ChartDto;
import croplanet.admin.common.util.FileManager;
import croplanet.admin.action.service.ActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
@Profile("local")
public class AdminController {

    private final ActionService actionService;
    private final ActionRepository actionRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationQueryRepository reservationQueryRepository;
    private final SurveyRepository surveyRepository;
    private final SurveyResponseRepository surveyResponseRepository;
    private final FileManager fileManager;

    @GetMapping("/table")
    public String table(@RequestParam(defaultValue = "0") int page, Model model){
        Page<Action> userMethods = actionRepository.findAll(PageRequest.of(page, 30));
        model.addAttribute("userMethods", userMethods);
        return "admin/user_method/table";
    }

    @GetMapping("/editor")
    public String editor(Model model){
        StringBuilder fileContent = fileManager.getMarketingComment();
        model.addAttribute("fileContent", fileContent.toString()); // 모델에 파일 내용 추가
        return "admin/editor";
    }

    @PostMapping("/editor")
    public ResponseEntity editor_save(@RequestBody String comment){
        try {
            fileManager.saveMarketingComment(comment);
            log.info(comment);
        } catch (IOException e){
            log.error("", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/reservation")
    public String reservation(
            @RequestParam(defaultValue = "0") int page,
            ReservationSearchCond reservationSearchCond,
            Model model
    ){

        log.info("param = {}", reservationSearchCond);

        //Page<Reservation> reservations = reservationRepository.findAll(PageRequest.of(page, 30));
        Page<Reservation> reservations = reservationQueryRepository.findAll(page, 30, reservationSearchCond);
        model.addAttribute("reservations", reservations);

        return "admin/reservation";

    }

    @GetMapping("/chart")
    public String chart(HttpServletRequest request, ChartDto chartDto, Model model){
        log.info("chartDto = {}", chartDto);

        if (chartDto.getStart_date() == null || chartDto.getStart_date().isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime oneWeekAgo = now.minus(1, ChronoUnit.WEEKS);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return "redirect:/admin/chart?start_date="+oneWeekAgo.format(formatter)+"&end_date="+now.format(formatter);

        }else {

            //해쉬 맵 생성 구조: key:날짜, value:해쉬 맵 (key:행동, value:카운트)
            Map<LocalDate, Map<String, Long>> map = new HashMap<>();

            //시작 날짜 부터 종료 날짜 까지 리스트 뽑기

            LocalDate start_date = LocalDate.parse(chartDto.getStart_date());
            LocalDate end_date = LocalDate.parse(chartDto.getEnd_date());

            List<LocalDate> dates = actionRepository.findDistinctDateByDateBetween(start_date, end_date);

            for (int i = 0; i < dates.size(); i++) {
                map.put(dates.get(i), new HashMap<>());
            }

            //행동 리스트 뽑기
            List<String> actions = actionRepository.findActionsDistinct();

            //날짜에 대한 행동의 카운트 뽑기
            for (int i = 0; i < dates.size(); i++) {
                for (int j = 0; j < actions.size(); j++) {
                    map.get(dates.get(i)).put(actions.get(j), actionRepository.countByDateAndAction(dates.get(i), actions.get(j)));
                    log.info("date={}, action={}, count={}", dates.get(i), actions.get(j), map.get(dates.get(i)).get(actions.get(j)));
                }
            }

            model.addAttribute("map", map);
            model.addAttribute("dates", dates);
            model.addAttribute("actions", actions);
        }


        return "admin/user_method/chart";
    }

    @GetMapping("/surveys/result")
    public String surveyResult(Model model){

        List<Survey> surveys = surveyRepository.findAll();
        List<SurveyResponse> surveyResponses = surveyResponseRepository.findAll();

        model.addAttribute("surveys", surveys);
        model.addAttribute("surveyResponses", surveyResponses);

        return "admin/survey/result";
    }

    @GetMapping("/test")
    public String test(){
        return "admin/test/layout";
    }

}
