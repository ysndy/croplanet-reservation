package croplanet.admin.survey.domain;

import croplanet.admin.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class SurveyResponse {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
