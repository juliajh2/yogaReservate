package yogareservation.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import yogareservation.config.kafka.KafkaProcessor;
import yogareservation.domain.*;

@Service
public class SearchClassViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private SearchClassRepository searchClassRepository;
    //>>> DDD / CQRS
}
