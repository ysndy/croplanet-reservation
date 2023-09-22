package croplanet.admin.domain;

import croplanet.admin.action.repository.ActionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ActionRepositoryTest {

    @Autowired private ActionRepository actionRepository;

    @Test
    void findRequestDomainDistinct() {
        //Assertions.assertThat(userMethodRepository.findRequestDomainDistinct().get(0)).isEqualTo("button1");
    }

    @Test
    void countByRequestDomainEquals() {
        //Assertions.assertThat(userMethodRepository.countByRequestDomainEquals("button1")).isEqualTo(1L);
    }
}