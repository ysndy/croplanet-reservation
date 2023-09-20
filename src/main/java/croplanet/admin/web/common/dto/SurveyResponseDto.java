package croplanet.admin.web.common.dto;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class SurveyResponseDto {

    private Map<Long, List<String>> responses = new LinkedHashMap<>();

}
