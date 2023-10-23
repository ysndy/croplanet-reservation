package croplanet.admin.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 570132775L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final ListPath<croplanet.admin.action.domain.Action, croplanet.admin.action.domain.QAction> actions = this.<croplanet.admin.action.domain.Action, croplanet.admin.action.domain.QAction>createList("actions", croplanet.admin.action.domain.Action.class, croplanet.admin.action.domain.QAction.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final croplanet.admin.reservation.domain.QReservation reservation;

    public final ListPath<croplanet.admin.survey.domain.SurveyResponse, croplanet.admin.survey.domain.QSurveyResponse> responses = this.<croplanet.admin.survey.domain.SurveyResponse, croplanet.admin.survey.domain.QSurveyResponse>createList("responses", croplanet.admin.survey.domain.SurveyResponse.class, croplanet.admin.survey.domain.QSurveyResponse.class, PathInits.DIRECT2);

    public final StringPath sessionId = createString("sessionId");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservation = inits.isInitialized("reservation") ? new croplanet.admin.reservation.domain.QReservation(forProperty("reservation")) : null;
    }

}

