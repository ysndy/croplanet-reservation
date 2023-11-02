package croplanet.admin.survey.service;

import croplanet.admin.survey.domain.Question;
import croplanet.admin.survey.domain.Survey;
import croplanet.admin.survey.dto.SurveyDto;
import croplanet.admin.survey.repository.QuestionRepository;
import croplanet.admin.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;

    public Long addSurvey(SurveyDto surveyDto){

        List<Question> questionList = surveyDto.getQuestions().stream()
                .map(questionString -> {
                    Question question = new Question();
                    question.setQuestion(questionString);
                    return question;
                }).collect(Collectors.toList());

        questionRepository.saveAll(questionList);

        Survey survey = new Survey();
        survey.setQuestions(questionList);
        survey.setTitle(surveyDto.getTitle());
        survey.setType(surveyDto.getType());
        surveyRepository.save(survey);

        return survey.getId();

    }

    public void deleteSurvey(Long id){

        Survey survey = surveyRepository.findById(id).orElseThrow();
        questionRepository.deleteBySurvey(survey);
        surveyRepository.deleteById(id);

    }

}
