package croplanet.admin.domain;

import croplanet.admin.web.common.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter @Setter
public class UserMethod extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;

    private String id;

    private String action;

    @Nullable
    private String query;

    public UserMethod(String id, String action, String query) {
        this.id = id;
        this.action = action;
        this.query = query;
    }

    public UserMethod() {

    }

    /**
     * 비즈니스 로직
     */

    public static UserMethod createUserMethod(String action, String query){
        return new UserMethod(UUID.randomUUID().toString(), action, query);
    }

}
