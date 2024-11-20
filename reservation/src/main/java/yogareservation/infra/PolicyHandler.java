package yogareservation.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import yogareservation.config.kafka.KafkaProcessor;
import yogareservation.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ReservationRepository reservationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SeatNumDecreased'"
    )
    public void wheneverSeatNumDecreased_UpdateStatus(
        @Payload SeatNumDecreased seatNumDecreased
    ) {
        SeatNumDecreased event = seatNumDecreased;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + seatNumDecreased + "\n\n"
        );

        // Sample Logic //
        Reservation.updateStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='NoSeatsLeft'"
    )
    public void wheneverNoSeatsLeft_UpdateStatus(
        @Payload NoSeatsLeft noSeatsLeft
    ) {
        NoSeatsLeft event = noSeatsLeft;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + noSeatsLeft + "\n\n"
        );

        // Sample Logic //
        Reservation.updateStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SeatNumIncreased'"
    )
    public void wheneverSeatNumIncreased_UpdateStatus(
        @Payload SeatNumIncreased seatNumIncreased
    ) {
        SeatNumIncreased event = seatNumIncreased;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + seatNumIncreased + "\n\n"
        );

        // Sample Logic //
        Reservation.updateStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
