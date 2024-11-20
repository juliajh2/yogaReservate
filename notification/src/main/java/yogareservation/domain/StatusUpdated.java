package yogareservation.domain;

import java.util.*;
import lombok.*;
import yogareservation.domain.*;
import yogareservation.infra.AbstractEvent;

@Data
@ToString
public class StatusUpdated extends AbstractEvent {

    private Long id;
    private String userId;
    private String classId;
    private String classDate;
    private String reserveId;
    private String reserveDate;
    private String status;
}
