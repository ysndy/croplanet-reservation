package croplanet.admin.action.repository;

import croplanet.admin.action.domain.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long>{

    @Query("select distinct u.action from Action u")
    List<String> findActionsDistinct();

    @Query("select distinct a.date from Action a")
    List<LocalDate> findDistinctDate();

    @Query("select distinct a.date from Action a where a.date between :startDate and :endDate")
    List<LocalDate> findDistinctDateByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    Long countByDateAndAction(LocalDate date, String action);

}
