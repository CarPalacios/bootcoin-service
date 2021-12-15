package KafkaProducer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.nttdata.model.Bootcoin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaProducer {
  
  private final KafkaTemplate<String, Object> kafkaTemplate;

  public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
      this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(Bootcoin bootcoin) {
      log.info("Producing message: {}", bootcoin.toString());
      this.kafkaTemplate.send("TOPIC-BOOTCOIN", bootcoin);
  }

}
