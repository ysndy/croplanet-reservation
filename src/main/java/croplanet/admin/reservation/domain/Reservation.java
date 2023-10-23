package croplanet.admin.reservation.domain;

import croplanet.admin.common.entity.BaseTimeEntity;
import croplanet.admin.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Reservation extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long kakaoId;

    private String profile_nickname;
    private String profile_image;
    private String account_email;
    private String name;
    private String gender;
    private String age_range;
    private String birthday;
    private String phone_number;
    private String shipping_address;

    @OneToMany
    private List<User> users;

}
