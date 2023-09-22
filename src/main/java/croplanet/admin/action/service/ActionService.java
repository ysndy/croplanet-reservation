package croplanet.admin.action.service;

import croplanet.admin.action.domain.Action;
import croplanet.admin.reservation.domain.Reservation;
import croplanet.admin.reservation.repository.ReservationRepository;
import croplanet.admin.action.repository.ActionRepository;
import croplanet.admin.user.domain.User;
import croplanet.admin.user.dto.KakaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ActionService {

    private final ActionRepository actionRepository;
    private final ReservationRepository reservationRepository;

    public Long save(User user, String action, String query){
        Action userMethod = new Action(user, action, query);
        return actionRepository.save(userMethod).getIndex();
    }

    public Long save(Action action){

        return actionRepository.save(action).getIndex();
    }

    public List<Action> getActions(){
        return actionRepository.findAll();
    }



}
