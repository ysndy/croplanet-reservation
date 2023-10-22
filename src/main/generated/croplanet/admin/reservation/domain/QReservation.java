package croplanet.admin.reservation.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = 275399193L;

    public static final QReservation reservation = new QReservation("reservation");

    public final croplanet.admin.common.entity.QBaseTimeEntity _super = new croplanet.admin.common.entity.QBaseTimeEntity(this);

    public final StringPath account_email = createString("account_email");

    public final StringPath age_range = createString("age_range");

    public final StringPath birthday = createString("birthday");

    //inherited
    public final DatePath<java.time.LocalDate> date = _super.date;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> dateTime = _super.dateTime;

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> kakaoId = createNumber("kakaoId", Long.class);

    public final StringPath name = createString("name");

    public final StringPath phone_number = createString("phone_number");

    public final StringPath profile_image = createString("profile_image");

    public final StringPath profile_nickname = createString("profile_nickname");

    public final StringPath shipping_address = createString("shipping_address");

    public QReservation(String variable) {
        super(Reservation.class, forVariable(variable));
    }

    public QReservation(Path<? extends Reservation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservation(PathMetadata metadata) {
        super(Reservation.class, metadata);
    }

}

