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
    YogaClassRepository yogaClassRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservePlaced'"
    )
    public void wheneverReservePlaced_DecreaseSeat(
        @Payload ReservePlaced reservePlaced
    ) {
        ReservePlaced event = reservePlaced;
        System.out.println(
            "\n\n##### listener DecreaseSeat : " + reservePlaced + "\n\n"
        );

        // Sample Logic //
        YogaClass.decreaseSeat(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReserveCanceled'"
    )
    public void wheneverReserveCanceled_IncreaseSeat(
        @Payload ReserveCanceled reserveCanceled
    ) {
        ReserveCanceled event = reserveCanceled;
        System.out.println(
            "\n\n##### listener IncreaseSeat : " + reserveCanceled + "\n\n"
        );

        // Sample Logic //
        YogaClass.increaseSeat(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
