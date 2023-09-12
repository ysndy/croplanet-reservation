package croplanet.admin.domain;

import croplanet.admin.web.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Log extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestDomain;

    public Log(String requestDomain) {
        this.requestDomain = requestDomain;
    }

    public Log() {

    }
}
