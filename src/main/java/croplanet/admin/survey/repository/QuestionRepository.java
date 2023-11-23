package croplanet.admin.survey.repository;

import croplanet.admin.survey.domain.Question;
import croplanet.admin.survey.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    void deleteBySurvey(Survey survey);

}
