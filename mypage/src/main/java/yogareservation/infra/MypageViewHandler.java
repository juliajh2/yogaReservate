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
public class MypageViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSeatNumIncreased_then_CREATE_1(
        @Payload SeatNumIncreased seatNumIncreased
    ) {
        try {
            if (!seatNumIncreased.validate()) return;

            // view 객체 생성
            Mypage mypage = new Mypage();
            // view 객체에 이벤트의 Value 를 set 함
            mypage.setClassId(seatNumIncreased.getClassId());
            mypage.setReserveStatus("reserved");
            // view 레파지 토리에 save
            mypageRepository.save(mypage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenNoSeatsLeft_then_CREATE_2(
        @Payload NoSeatsLeft noSeatsLeft
    ) {
        try {
            if (!noSeatsLeft.validate()) return;

            // view 객체 생성
            Mypage mypage = new Mypage();
            // view 객체에 이벤트의 Value 를 set 함
            mypage.setClassId(noSeatsLeft.getClassId());
            mypage.setReserveStatus("not available");
            // view 레파지 토리에 save
            mypageRepository.save(mypage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserveCanceled_then_UPDATE_1(
        @Payload ReserveCanceled reserveCanceled
    ) {
        try {
            if (!reserveCanceled.validate()) return;
            // view 객체 조회

            List<Mypage> mypageList = mypageRepository.findByClassId(
                reserveCanceled.getClassId()
            );
            for (Mypage mypage : mypageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setReserveStatus("canceled");
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
