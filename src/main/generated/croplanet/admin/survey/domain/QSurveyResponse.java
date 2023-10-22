package croplanet.admin.survey.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSurveyResponse is a Querydsl query type for SurveyResponse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveyResponse extends EntityPathBase<SurveyResponse> {

    private static final long serialVersionUID = 2055159078L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSurveyResponse surveyResponse = new QSurveyResponse("surveyResponse");

    public final StringPath answer = createString("answer");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSurvey survey;

    public final croplanet.admin.user.domain.QUser user;

    public QSurveyResponse(String variable) {
        this(SurveyResponse.class, forVariable(variable), INITS);
    }

    public QSurveyResponse(Path<? extends SurveyResponse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSurveyResponse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSurveyResponse(PathMetadata metadata, PathInits inits) {
        this(SurveyResponse.class, metadata, inits);
    }

    public QSurveyResponse(Class<? extends SurveyResponse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.survey = inits.isInitialized("survey") ? new QSurvey(forProperty("survey")) : null;
        this.user = inits.isInitialized("user") ? new croplanet.admin.user.domain.QUser(forProperty("user")) : null;
    }

}

