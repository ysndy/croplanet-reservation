package croplanet.admin.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KakaoDTO {

    private long id;
    private String profile_nickname;
    private String profile_image;
    private String account_email;
    private String name;
    private String gender;
    private String age_range;
    private String birthday;
    private String phone_number;
    private String shipping_address;

}
