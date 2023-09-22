package croplanet.admin.user.domain;

import croplanet.admin.action.domain.Action;
import croplanet.admin.survey.domain.SurveyResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "member")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String kakaoId;

    private String name;

    private String sessionId;

    @OneToMany
    private List<SurveyResponse> responses;

    @OneToMany
    private List<Action> actions;

}
