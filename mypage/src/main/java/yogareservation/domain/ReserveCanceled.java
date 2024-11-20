package yogareservation.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import yogareservation.infra.AbstractEvent;

@Data
public class ReserveCanceled extends AbstractEvent {

    private Long id;
    private String userId;
    private String classId;
    private String classDate;
    private String reserveId;
    private String reserveDate;
    private String status;
}
