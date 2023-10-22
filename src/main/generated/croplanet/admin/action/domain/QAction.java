package croplanet.admin.action.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAction is a Querydsl query type for Action
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAction extends EntityPathBase<Action> {

    private static final long serialVersionUID = 694539901L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAction action1 = new QAction("action1");

    public final croplanet.admin.common.entity.QBaseTimeEntity _super = new croplanet.admin.common.entity.QBaseTimeEntity(this);

    public final StringPath action = createString("action");

    //inherited
    public final DatePath<java.time.LocalDate> date = _super.date;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> dateTime = _super.dateTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath query = createString("query");

    public final croplanet.admin.user.domain.QUser user;

    public QAction(String variable) {
        this(Action.class, forVariable(variable), INITS);
    }

    public QAction(Path<? extends Action> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAction(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAction(PathMetadata metadata, PathInits inits) {
        this(Action.class, metadata, inits);
    }

    public QAction(Class<? extends Action> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new croplanet.admin.user.domain.QUser(forProperty("user")) : null;
    }

}

