package yogareservation.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import yogareservation.infra.AbstractEvent;

@Data
public class NoSeatsLeft extends AbstractEvent {

    private Long id;
    private String classId;
    private String seatNum;
    private String classDate;
    private Integer maxSeat;
    private Integer reservedSeat;
}
