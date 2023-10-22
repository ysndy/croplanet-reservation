package croplanet.admin.reservation.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import croplanet.admin.reservation.domain.QReservation;
import croplanet.admin.reservation.domain.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static croplanet.admin.reservation.domain.QReservation.reservation;

@Repository
public class ReservationQueryRepository {

    private final JPAQueryFactory query;

    public ReservationQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public Page<Reservation> findAll(int page, int limit, ReservationSearchCond cond) {
        JPAQuery<Reservation> jpaQuery = query.select(reservation)
                .from(reservation)
                .where(
                        dateBetween(cond.getStartDate(), cond.getEndDate()),
                        likeCondition(reservation.name, cond.getName()),
                        likeCondition(reservation.account_email, cond.getEmail()),
                        likeCondition(reservation.phone_number, cond.getPhoneNumber())
                );

        // 페이지네이션을 적용하기 위해 Pageable 객체를 생성
        Pageable pageable = PageRequest.of(page, limit);

        // QueryResults를 사용하여 전체 결과와 페이지 정보를 가져옴
        QueryResults<Reservation> results = jpaQuery.limit(limit)
                .offset(limit * page)
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
    private BooleanExpression likeCondition(StringPath field, String value) {
        if(StringUtils.hasText(value)){
            return field.like("%"+value+"%");
        }
        return null;
    }

    private BooleanExpression dateBetween(String startDate, String endDate){

        if(startDate != null && endDate != null && StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
            return reservation.date.between(LocalDate.parse(startDate), LocalDate.parse(endDate));
        }
        return null;
    }

}
