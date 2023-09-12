package croplanet.admin.web.service;

import croplanet.admin.domain.Log;
import croplanet.admin.domain.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    public Long save(Log log){
        return logRepository.save(log).getId();
    }

    public List<String> getRequests(){
        return logRepository.findRequestDomainDistinct();
    }

    public Long getCountByRequest(String request){
        return logRepository.countByRequestDomainEquals(request);
    }

    public List<String> getAllDates(){
        return logRepository.findAllDates();
    }

    public Long getCountByRequestAndDate(String request, String date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        return logRepository.countByRequestDomainByCreatedDate(request, LocalDateTime.parse(date, formatter));
    }

}
