package croplanet.admin;

import croplanet.admin.domain.Log;
import croplanet.admin.domain.LogRepository;
import croplanet.admin.web.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final LogService logService;

    @PostConstruct
    private void init(){
        Log log1 = new Log();
        log1.setRequestDomain("button1");
        //logService.save(log1);
    }

}
