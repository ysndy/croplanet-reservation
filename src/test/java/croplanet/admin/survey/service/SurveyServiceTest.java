package croplanet.admin.survey.service;

import croplanet.admin.survey.domain.Question;
import croplanet.admin.survey.domain.Survey;
import croplanet.admin.survey.domain.enums.SurveyType;
import croplanet.admin.survey.dto.SurveyDto;
import croplanet.admin.survey.repository.SurveyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SurveyServiceTest {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyRepository surveyRepository;

    @Test
    void addSurvey(){

        SurveyDto surveyDto = new SurveyDto();
        surveyDto.setTitle("test");
        surveyDto.setType(SurveyType.TEXT);

        List<String> questions = new LinkedList<>();

        questions.add("질문1");
        questions.add("질문2");

        surveyDto.setQuestions(questions);

        Long saveId = surveyService.addSurvey(surveyDto);

        Survey findSurvey = surveyRepository.findById(saveId).orElseThrow();

        assertThat(findSurvey.getTitle()).isEqualTo("test");
        assertThat(findSurvey.getType()).isEqualTo(SurveyType.TEXT);
        assertThat(findSurvey.getQuestions().get(0).getQuestion()).isEqualTo("질문1");
        assertThat(findSurvey.getQuestions().get(1).getQuestion()).isEqualTo("질문2");

    }

}