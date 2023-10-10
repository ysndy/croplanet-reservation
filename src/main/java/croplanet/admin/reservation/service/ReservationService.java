package croplanet.admin.reservation.service;

import croplanet.admin.reservation.domain.Reservation;
import croplanet.admin.reservation.repository.ReservationRepository;
import croplanet.admin.user.dto.KakaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation addReservation(KakaoDTO kakaoDTO){

        Reservation reservation;
        Optional optional = reservationRepository.findByKakaoId(kakaoDTO.getId());
        if(optional.isPresent()){
            reservation = (Reservation) optional.get();
        }else {
            reservation = new Reservation();
            reservation.setKakaoId(kakaoDTO.getId());
            reservation.setName(kakaoDTO.getProfile_nickname());
            reservation.setAge_range(kakaoDTO.getAge_range());
            reservation.setBirthday(kakaoDTO.getBirthday());
            reservation.setGender(kakaoDTO.getGender());
            reservation.setPhone_number(kakaoDTO.getPhone_number());
            reservation.setProfile_image(kakaoDTO.getProfile_image());
            reservation.setProfile_nickname(kakaoDTO.getProfile_nickname());
            reservationRepository.save(reservation);
        }

        return reservation;

    }

}
