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
            reservation.setName(kakaoDTO.getNickname());
            reservationRepository.save(reservation);
        }

        return reservation;

    }

}
