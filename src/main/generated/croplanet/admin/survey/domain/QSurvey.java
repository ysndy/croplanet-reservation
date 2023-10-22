package croplanet.admin.survey.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSurvey is a Querydsl query type for Survey
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurvey extends EntityPathBase<Survey> {

    private static final long serialVersionUID = -1532143163L;

    public static final QSurvey survey = new QSurvey("survey");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isEssential = createBoolean("isEssential");

    public final ListPath<Question, QQuestion> questions = this.<Question, QQuestion>createList("questions", Question.class, QQuestion.class, PathInits.DIRECT2);

    public final ListPath<SurveyResponse, QSurveyResponse> surveyResponses = this.<SurveyResponse, QSurveyResponse>createList("surveyResponses", SurveyResponse.class, QSurveyResponse.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final EnumPath<croplanet.admin.survey.domain.enums.SurveyType> type = createEnum("type", croplanet.admin.survey.domain.enums.SurveyType.class);

    public QSurvey(String variable) {
        super(Survey.class, forVariable(variable));
    }

    public QSurvey(Path<? extends Survey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSurvey(PathMetadata metadata) {
        super(Survey.class, metadata);
    }

}

