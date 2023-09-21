package croplanet.admin.survey.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SurveyType {

    RADIO("radio"), CHECKBOX("checkbox"), TEXT("text");
    private final String description;

}
