package yogareservation.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import yogareservation.domain.*;
import yogareservation.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReservePlaced extends AbstractEvent {

    private Long id;
    private String userId;
    private String classId;
    private String classDate;
    private String reserveId;
    private String reserveDate;
    private String status;

    public ReservePlaced(Reservation aggregate) {
        super(aggregate);
    }

    public ReservePlaced() {
        super();
    }
}
//>>> DDD / Domain Event
