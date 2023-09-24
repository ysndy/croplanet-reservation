package croplanet.admin.action.service;

import croplanet.admin.action.domain.Action;
import croplanet.admin.reservation.repository.ReservationRepository;
import croplanet.admin.action.repository.ActionRepository;
import croplanet.admin.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ActionService {

    private final ActionRepository actionRepository;
    private final ReservationRepository reservationRepository;

    public Long save(User user, String action, String query){
        Action userMethod = new Action(user, action, query);
        return actionRepository.save(userMethod).getId();
    }

    public Long save(Action action){

        return actionRepository.save(action).getId();
    }

    public List<Action> getActions(){
        return actionRepository.findAll();
    }



}
