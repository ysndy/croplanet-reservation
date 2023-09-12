package croplanet.admin.domain;

import lombok.Data;

@Data
public class RequestDomain {

    private String requestDomain;
    private Long count;

    public RequestDomain(String requestDomain, Long count) {
        this.requestDomain = requestDomain;
        this.count = count;
    }
}
