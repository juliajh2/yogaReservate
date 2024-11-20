package yogareservation.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import yogareservation.domain.*;
import yogareservation.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReserveCanceled extends AbstractEvent {

    private Long id;
    private String userId;
    private String classId;
    private String classDate;
    private String reserveId;
    private String reserveDate;
    private String status;

    public ReserveCanceled(Reservation aggregate) {
        super(aggregate);
    }

    public ReserveCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
