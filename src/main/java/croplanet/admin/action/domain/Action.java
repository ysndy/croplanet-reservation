package croplanet.admin.action.domain;

import croplanet.admin.common.entity.BaseTimeEntity;
import croplanet.admin.user.domain.User;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Action extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String action;

    @Nullable
    private String query;

    public Action(User user, String action, String query) {
        this.user = user;
        this.action = action;
        this.query = query;
    }

    public Action() {

    }

    /**
     * 비즈니스 로직
     */

}
