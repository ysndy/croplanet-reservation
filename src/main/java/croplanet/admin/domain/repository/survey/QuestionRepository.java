package croplanet.admin.domain.repository.survey;

import croplanet.admin.domain.entity.survey.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
