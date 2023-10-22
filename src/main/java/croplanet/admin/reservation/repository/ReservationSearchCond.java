package croplanet.admin.reservation.repository;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationSearchCond {

    private LocalDate startDate;
    private LocalDate endDate;
    private String name;
    private String phoneNumber;
    private String email;

}
