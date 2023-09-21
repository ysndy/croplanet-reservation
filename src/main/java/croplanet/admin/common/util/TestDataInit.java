package croplanet.admin.common.util;


import croplanet.admin.survey.domain.Question;
import croplanet.admin.survey.domain.Survey;
import croplanet.admin.survey.domain.enums.SurveyType;
import croplanet.admin.survey.repository.QuestionRepository;
import croplanet.admin.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataInit {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;

    @PostConstruct
    private void init(){

        List<Question> questions = new ArrayList<>();

        Question question1 = new Question();
        question1.setQuestion("5000원 ~ 10000원");
        questions.add(question1);
        questionRepository.save(question1);

        Question question2 = new Question();
        question2.setQuestion("15000원 ~ 30000원");
        questions.add(question2);
        questionRepository.save(question2);

        Question question3 = new Question();
        question3.setQuestion("35000원 ~ 50000원");
        questions.add(question3);
        questionRepository.save(question3);

        Question question4 = new Question();
        question4.setQuestion("55000원 ~ 80000원");
        questions.add(question4);
        questionRepository.save(question4);

        Question question5 = new Question();
        question5.setQuestion("85000원 ~ 100000원");
        questions.add(question5);
        questionRepository.save(question5);

        Survey survey = new Survey();

        survey.setTitle("캐릭터 굿즈 구매는 월 얼마정도 하시나요?");
        survey.setQuestions(questions);
        survey.setType(SurveyType.CHECKBOX);
        survey.setIsEssential(false);

        surveyRepository.save(survey);

        questions = new ArrayList<>();

        question1 = new Question();
        question1.setQuestion("5000원 ~ 10000원");
        questions.add(question1);
        questionRepository.save(question1);

        question2 = new Question();
        question2.setQuestion("15000원 ~ 30000원");
        questions.add(question2);
        questionRepository.save(question2);

        question3 = new Question();
        question3.setQuestion("35000원 ~ 50000원");
        questions.add(question3);
        questionRepository.save(question3);

        question4 = new Question();
        question4.setQuestion("55000원 ~ 80000원");
        questions.add(question4);
        questionRepository.save(question4);

        question5 = new Question();
        question5.setQuestion("85000원 ~ 100000원");
        questions.add(question5);
        questionRepository.save(question5);

        survey = new Survey();

        survey.setTitle("캐릭터 굿즈 구매는 월 얼마정도 하시나요?");
        survey.setQuestions(questions);
        survey.setType(SurveyType.RADIO);
        survey.setIsEssential(false);

        surveyRepository.save(survey);

        questions = new ArrayList<>();

        question1 = new Question();
        question1.setQuestion("");
        questions.add(question1);
        questionRepository.save(question1);

        survey = new Survey();

        survey.setTitle("캐릭터 굿즈 구매는 월 얼마정도 하시나요?");
        survey.setQuestions(questions);
        survey.setType(SurveyType.TEXT);
        survey.setIsEssential(false);

        surveyRepository.save(survey);

    }

}