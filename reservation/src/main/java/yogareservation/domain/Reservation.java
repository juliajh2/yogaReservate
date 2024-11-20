package yogareservation.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import yogareservation.ReservationApplication;
import yogareservation.domain.ReserveCanceled;
import yogareservation.domain.ReservePlaced;
import yogareservation.domain.StatusUpdated;

@Entity
@Table(name = "Reservation_table")
@Data
//<<< DDD / Aggregate Root
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String classId;

    private String classDate;

    private String reserveId;

    private String reserveDate;

    private String status;

    @PostPersist
    public void onPostPersist() {
        ReservePlaced reservePlaced = new ReservePlaced(this);
        reservePlaced.publishAfterCommit();

        ReserveCanceled reserveCanceled = new ReserveCanceled(this);
        reserveCanceled.publishAfterCommit();

        StatusUpdated statusUpdated = new StatusUpdated(this);
        statusUpdated.publishAfterCommit();
    }

    public static ReservationRepository repository() {
        ReservationRepository reservationRepository = ReservationApplication.applicationContext.getBean(
            ReservationRepository.class
        );
        return reservationRepository;
    }

    //<<< Clean Arch / Port Method
    public static void updateStatus(SeatNumDecreased seatNumDecreased) {
        //implement business logic here:
        StatusUpdated statusUpdated = new StatusUpdated();
        statusUpdated.setClassId(seatNumDecreased.getClassId());
        statusUpdated.setStatus("reserved");
        statusUpdated.publishAfterCommit();

        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        StatusUpdated statusUpdated = new StatusUpdated(reservation);
        statusUpdated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(seatNumDecreased.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);

            StatusUpdated statusUpdated = new StatusUpdated(reservation);
            statusUpdated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateStatus(NoSeatsLeft noSeatsLeft) {
        //implement business logic here:
        StatusUpdated statusUpdated = new StatusUpdated();
        statusUpdated.setClassId(noSeatsLeft.getClassId());
        statusUpdated.setStatus("not available");
        statusUpdated.publishAfterCommit();
        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        StatusUpdated statusUpdated = new StatusUpdated(reservation);
        statusUpdated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(noSeatsLeft.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);

            StatusUpdated statusUpdated = new StatusUpdated(reservation);
            statusUpdated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateStatus(SeatNumIncreased seatNumIncreased) {
        //implement business logic here:
        StatusUpdated statusUpdated = new StatusUpdated();
        statusUpdated.setClassId(seatNumIncreased.getClassId());
        statusUpdated.setStatus("canceld");
        statusUpdated.publishAfterCommit();
        
        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        StatusUpdated statusUpdated = new StatusUpdated(reservation);
        statusUpdated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(seatNumIncreased.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);

            StatusUpdated statusUpdated = new StatusUpdated(reservation);
            statusUpdated.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
