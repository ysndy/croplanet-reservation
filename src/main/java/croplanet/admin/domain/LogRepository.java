package croplanet.admin.domain;

import croplanet.admin.domain.Log;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long>{

    //Long save(Log log);
    //Long count();

    //요청 url 목록을 조회
    @Query("select distinct l.requestDomain from Log l")
    List<String> findRequestDomainDistinct();

    //요청 url 횟수를 조회
    long countByRequestDomainEquals(String requestDomain);

    //전체 날짜 불러오기
    @Query(value = "SELECT DATE_TRUNC('DAY', l.CREATED_DATE) AS truncated_date " +
            "FROM Log l " +
            "GROUP BY DATE_TRUNC('DAY', l.CREATED_DATE)", nativeQuery = true)
    List<String> findAllDates();

    //requestDomain 에 대한 특정 날짜에 대한 count

    @Query(value = "SELECT COUNT(*) " +
            "FROM Log l " +
            "WHERE l.request_Domain = :requestDomain AND DATE_TRUNC('DAY', l.created_Date) = DATE_TRUNC('DAY', :createdDate) " +
            "GROUP BY DATE_TRUNC('DAY', l.CREATED_DATE)", nativeQuery = true)

    Long countByRequestDomainByCreatedDate(@Param("requestDomain") String requestDomain, @Param("createdDate") LocalDateTime createdDate);

}
