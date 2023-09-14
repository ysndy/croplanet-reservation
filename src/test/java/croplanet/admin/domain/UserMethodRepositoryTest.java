package croplanet.admin.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMethodRepositoryTest {

    @Autowired private UserMethodRepository userMethodRepository;

    @Test
    void findRequestDomainDistinct() {
        Assertions.assertThat(userMethodRepository.findRequestDomainDistinct().get(0)).isEqualTo("button1");
    }

    @Test
    void countByRequestDomainEquals() {
        Assertions.assertThat(userMethodRepository.countByRequestDomainEquals("button1")).isEqualTo(1L);
    }
}