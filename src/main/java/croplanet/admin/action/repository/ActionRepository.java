package croplanet.admin.action.repository;

import croplanet.admin.action.domain.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long>{

    //Long save(Log log);
    //Long count();

    //요청 url 목록을 조회
    @Query("select distinct u.action from Action u")
    List<String> findActionsDistinct();

//    //요청 url 횟수를 조회
//    long countByRequestDomainEquals(String requestDomain);
//
    //전체 날짜 불러오기
    @Query(value = "SELECT DATE_TRUNC('DAY', u.date) AS truncated_date " +
            "FROM Action u " +
            "GROUP BY DATE_TRUNC('DAY', u.date)", nativeQuery = true)
    List<String> findAllDates();

    //전체 날짜 불러오기
    @Query(value = "SELECT DATE_TRUNC('DAY', u.date) AS truncated_date " +
            "FROM Action u " +
            "WHERE DATE_TRUNC('DAY', u.date) BETWEEN :startDate AND :endDate " +
            "GROUP BY DATE_TRUNC('DAY', u.date)", nativeQuery = true)
    List<String> findDates(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT COUNT(*) from Action u where DATE_TRUNC('DAY', u.date) = :date and u.action = :action", nativeQuery = true)
    Long findByDateAndAction(@Param("date") String date, @Param("action") String action);


//    //requestDomain 에 대한 특정 날짜에 대한 count
//
//    @Query(value = "SELECT COUNT(*) " +
//            "FROM Log l " +
//            "WHERE l.request_Domain = :requestDomain AND DATE_TRUNC('DAY', l.created_Date) = DATE_TRUNC('DAY', :createdDate) " +
//            "GROUP BY DATE_TRUNC('DAY', l.CREATED_DATE)", nativeQuery = true)
//
//    Long countByRequestDomainByCreatedDate(@Param("requestDomain") String requestDomain, @Param("createdDate") LocalDateTime createdDate);



}
