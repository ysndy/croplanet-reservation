package croplanet.admin.reservation.repository;

import croplanet.admin.reservation.domain.Reservation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReservationQueryRepositoryTest {

    @Autowired
    private ReservationQueryRepository queryRepository;

    @Autowired
    private ReservationRepository repository;

    @Test
    void findAll(){
        Reservation reservation1 = new Reservation();
        reservation1.setName("조도연");
        reservation1.setAccount_email("ysndy@naver.com");
        reservation1.setPhone_number("010-224-578");

        Reservation reservation2 = new Reservation();
        reservation2.setName("홍자동");
        reservation2.setAccount_email("hong@naver.com");
        reservation2.setPhone_number("010-223-233");

        Reservation reservation3 = new Reservation();
        reservation3.setName("김감자");
        reservation3.setAccount_email("email@naver.com");
        reservation3.setPhone_number("010-444-181");

        repository.save(reservation1);
        repository.save(reservation2);
        repository.save(reservation3);

        test("조", null, null, reservation1);
        test("자", null, null, reservation2, reservation3);

        test(null, "@naver.com", null, reservation1, reservation2, reservation3);

        test(null, null, "010-2", reservation1, reservation2);

    }

    void test(String name, String email, String phoneNumber, Reservation... reservations){
        ReservationSearchCond cond = new ReservationSearchCond();
        cond.setName(name);
        cond.setEmail(email);
        cond.setPhoneNumber(phoneNumber);
        List<Reservation> result = queryRepository.findAll(0, 100, cond).toList();
        Assertions.assertThat(result).containsExactly(reservations);
    }

}