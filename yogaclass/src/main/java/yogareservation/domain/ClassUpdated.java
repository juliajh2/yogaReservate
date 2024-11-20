package yogareservation.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import yogareservation.domain.*;
import yogareservation.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ClassUpdated extends AbstractEvent {

    private Long id;
    private String classId;
    private String seatNum;
    private String classDate;
    private Integer maxSeat;
    private Integer reservedSeat;

    public ClassUpdated(YogaClass aggregate) {
        super(aggregate);
    }

    public ClassUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
