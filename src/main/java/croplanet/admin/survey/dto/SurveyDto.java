package croplanet.admin.survey.dto;

import croplanet.admin.survey.domain.enums.SurveyType;
import lombok.Data;

import java.util.List;

@Data
public class SurveyDto {

    private String title;
    private SurveyType type;
    private List<String> questions;

}
