package croplanet.admin.reservation.repository;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationSearchCond {

    private String startDate;
    private String endDate;
    private String name;
    private String phoneNumber;
    private String email;

}
