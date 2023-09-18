package croplanet.admin.web.admin.controller;

import croplanet.admin.domain.entity.UserMethod;
import croplanet.admin.domain.repository.UserMethodRepository;
import croplanet.admin.web.common.util.FileManager;
import croplanet.admin.web.user.service.UserMethodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final UserMethodService userMethodService;
    private final UserMethodRepository userMethodRepository;
    private final FileManager fileManager;

    @GetMapping("/table")
    public String table(@RequestParam(defaultValue = "0") int page, Model model){
        Page<UserMethod> userMethods = userMethodRepository.findAll(PageRequest.of(page, 30));
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
        } catch (IOException e){
            log.error("", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @return
     */

    //TODO 그래프 형식 선택
    //TODO 날짜별 선 그래프 에서 기간 선택
//    @GetMapping()
//    public String chart(@ModelAttribute UserMethod userMethod, Model model){
//
////        List<RequestDomain> requestDomains = logService.getRequests()
////                .stream().map(r -> new RequestDomain(r, logService.getCountByRequest(r)))
////                .collect(Collectors.toList());
////
////        model.addAttribute("requestDomains", requestDomains);
//
//        //모든 요청 저장
//        List<String> logs = userMethodService.getRequests();
//
//        //요청에 대한 값들 저장
//        List<Long> countsByLogs = logs.stream()
//                .map(r -> userMethodService.getCountByRequest(r))
//                .collect(Collectors.toList());
//
//        //요청, 값 해쉬 저장
//        Map<String, Long> countsMap = new HashMap<>();
//        for(int i=0; i<logs.size(); i++){
//            countsMap.put(logs.get(i), countsByLogs.get(i));
//        }
//
//        //모든 날짜 저장
//        List<String> dateTimes = userMethodService.getAllDates();
//        System.out.println(dateTimes);
//
////        //날짜에 대한 버튼의 값 저장
////        Map<String, List<Long>> countsByDateTimesMap = new HashMap<>();
////        for(int i=0; i<logs.size(); i++){
////            List<Long> counts = new ArrayList<>();
////            for(int j=0; j<dateTimes.size(); j++){
////                counts.add(logService.getCountByRequestAndDate(logs.get(i), dateTimes.get(j)));
////            }
////            System.out.println(counts);
////            countsByDateTimesMap.put(logs.get(i), counts);
////        }
//
//        //모델에 추가
//        model.addAttribute("logs", logs);
//        model.addAttribute("countsByLogs", countsByLogs);
//        model.addAttribute("countsMap", countsMap);
//        model.addAttribute("dateTimes", dateTimes);
//        //model.addAttribute("countsByDateTimesMap", countsByDateTimesMap);
//
//        return "/admin/chart";
//    }

    @PostMapping("/refresh")
    public String refresh(){
        return "redirect:/admin/";
    }

}
