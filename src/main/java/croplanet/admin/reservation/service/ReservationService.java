package croplanet.admin.reservation.service;

import croplanet.admin.reservation.domain.Reservation;
import croplanet.admin.reservation.repository.ReservationRepository;
import croplanet.admin.user.domain.User;
import croplanet.admin.user.dto.KakaoDTO;
import croplanet.admin.user.dto.UserDto;
import croplanet.admin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public Reservation addReservation(KakaoDTO kakaoDTO, UserDto userDto){

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
            reservation.setAccount_email(kakaoDTO.getAccount_email());
            reservationRepository.save(reservation);

            User user = userRepository.findById(userDto.getUserId()).orElseThrow();
            user.setReservation(reservation);

        }

        return reservation;

    }

}
