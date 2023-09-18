package croplanet.admin.web.user.service;

import croplanet.admin.domain.entity.Reservation;
import croplanet.admin.domain.entity.UserMethod;
import croplanet.admin.domain.repository.ReservationRepository;
import croplanet.admin.domain.repository.UserMethodRepository;
import croplanet.admin.web.user.dto.KakaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserMethodService {

    private final UserMethodRepository userMethodRepository;
    private final ReservationRepository reservationRepository;

    public Long save(String user, String action, String query){
        UserMethod userMethod = new UserMethod(user, action, query);
        return userMethodRepository.save(userMethod).getIndex();
    }

    public Long save(UserMethod userMethod){

        return userMethodRepository.save(userMethod).getIndex();
    }

    public List<UserMethod> getUserMethods(){
        return userMethodRepository.findAll();
    }

    public Reservation addReservation(KakaoDTO kakaoDTO){

        Reservation reservation;
        Optional optional = reservationRepository.findByKakaoId(kakaoDTO.getId());
        if(optional.isPresent()){
            reservation = (Reservation) optional.get();
        }else {
            reservation = new Reservation();
            reservation.setKakaoId(kakaoDTO.getId());
            reservationRepository.save(reservation);
        }

        return reservation;

    }

//    public List<String> getRequests(){
//        return userMethodRepository.findRequestDomainDistinct();
//    }
//
//    public Long getCountByRequest(String request){
//        return userMethodRepository.countByRequestDomainEquals(request);
//    }
//
//    public List<String> getAllDates(){
//        return userMethodRepository.findAllDates();
//    }
//
//    public Long getCountByRequestAndDate(String request, String date){
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
//        return userMethodRepository.countByRequestDomainByCreatedDate(request, LocalDateTime.parse(date, formatter));
//    }

}
