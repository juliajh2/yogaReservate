package yogareservation.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import yogareservation.YogaclassApplication;
import yogareservation.domain.ClassAdded;
import yogareservation.domain.ClassDeleted;
import yogareservation.domain.ClassUpdated;
import yogareservation.domain.NoSeatsLeft;
import yogareservation.domain.SeatNumDecreased;
import yogareservation.domain.SeatNumIncreased;

@Entity
@Table(name = "YogaClass_table")
@Data
//<<< DDD / Aggregate Root
public class YogaClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String classId;

    private String classDate;

    private Integer maxSeat;

    private Integer reservedSeat;

    @PostPersist
    public void onPostPersist() {
        SeatNumDecreased seatNumDecreased = new SeatNumDecreased(this);
        seatNumDecreased.publishAfterCommit();

        NoSeatsLeft noSeatsLeft = new NoSeatsLeft(this);
        noSeatsLeft.publishAfterCommit();

        ClassAdded classAdded = new ClassAdded(this);
        classAdded.publishAfterCommit();

        ClassUpdated classUpdated = new ClassUpdated(this);
        classUpdated.publishAfterCommit();

        ClassDeleted classDeleted = new ClassDeleted(this);
        classDeleted.publishAfterCommit();

        SeatNumIncreased seatNumIncreased = new SeatNumIncreased(this);
        seatNumIncreased.publishAfterCommit();
    }

    public static YogaClassRepository repository() {
        YogaClassRepository yogaClassRepository = YogaclassApplication.applicationContext.getBean(
            YogaClassRepository.class
        );
        return yogaClassRepository;
    }

    //<<< Clean Arch / Port Method
    public static void increaseReserveSeat(ReservePlaced reservePlaced) {
        //implement business logic here:
        repository().findById(Long.valueOf(reservePlaced.getClassId())).ifPresent(yogaClass->{
            
            //예약가능
            if(yogaClass.getReservedSeat() < yogaClass.getMaxSeat()){
                yogaClass.setReservedSeat(yogaClass.getReservedSeat() + 1);
                repository().save(yogaClass);

                SeatNumIncreased seatNumIncreased = new SeatNumIncreased(yogaClass);
                seatNumIncreased.publishAfterCommit();
            }
            //예약불가능
            else{
                NoSeatsLeft noSeatsLeft = new NoSeatsLeft(yogaClass);
                noSeatsLeft.publishAfterCommit();
            }

         });
        /** Example 1:  new item 
        YogaClass yogaClass = new YogaClass();
        repository().save(yogaClass);

        SeatNumIncreased seatNumIncreased = new SeatNumIncreased(yogaClass);
        seatNumIncreased.publishAfterCommit();
        NoSeatsLeft noSeatsLeft = new NoSeatsLeft(yogaClass);
        noSeatsLeft.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(reservePlaced.get???()).ifPresent(yogaClass->{
            
            yogaClass // do something
            repository().save(yogaClass);

            SeatNumIncreased seatNumIncreased = new SeatNumIncreased(yogaClass);
            seatNumIncreased.publishAfterCommit();
            NoSeatsLeft noSeatsLeft = new NoSeatsLeft(yogaClass);
            noSeatsLeft.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void decreaseReserveSeat(ReserveCanceled reserveCanceled) {
        //implement business logic here:

        repository().findById(Long.valueOf(reserveCanceled.getClassId())).ifPresent(yogaClass->{
            
            repository().save(yogaClass);

            SeatNumDecreased seatNumDecreased = new SeatNumDecreased(yogaClass);
            seatNumDecreased.publishAfterCommit();

         });
        /** Example 1:  new item 
        YogaClass yogaClass = new YogaClass();
        repository().save(yogaClass);

        SeatNumDecreased seatNumDecreased = new SeatNumDecreased(yogaClass);
        seatNumDecreased.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(reserveCanceled.get???()).ifPresent(yogaClass->{
            
            yogaClass // do something
            repository().save(yogaClass);

            SeatNumDecreased seatNumDecreased = new SeatNumDecreased(yogaClass);
            seatNumDecreased.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
