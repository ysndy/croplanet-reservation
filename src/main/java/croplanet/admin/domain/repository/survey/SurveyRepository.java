package croplanet.admin.domain.repository.survey;

import croplanet.admin.domain.entity.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
