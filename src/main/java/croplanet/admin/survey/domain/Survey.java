package croplanet.admin.survey.domain;

import croplanet.admin.survey.domain.enums.SurveyType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@ToString
public class Survey {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany
    private List<Question> questions;

    private SurveyType type; //radio, checkbox, text ...

    private Boolean isEssential;

    @OneToMany
    private List<SurveyResponse> surveyResponses;

}
