package croplanet.admin.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LogRepositoryTest {

    @Autowired private LogRepository logRepository;

    @Test
    void findRequestDomainDistinct() {
        Assertions.assertThat(logRepository.findRequestDomainDistinct().get(0)).isEqualTo("button1");
    }

    @Test
    void countByRequestDomainEquals() {
        Assertions.assertThat(logRepository.countByRequestDomainEquals("button1")).isEqualTo(1L);
    }
}