package it.pi.gamma.project.v1.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.pi.gamma.project.exception.APIException;
import it.pi.gamma.project.exception.constant.api.IAPIException;
import it.pi.gamma.project.model.GPOperation;
import it.pi.gamma.project.util.KafkaUtil;
import it.pi.gamma.project.util.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogService {
	
	@Value("${spring.kafka.template.log-topic}")
	private String logTopic;
	
	@Autowired
	private KafkaProducer<String,String> kafkaProducer;
	
	public void sendEvent(GPOperation operation, String message) throws APIException {
		log.info("[INFO] Entering method: sendEvent. Params [operation: "+operation+"]. Start at: "+Utils.getCurrentTimeStamp());
		
		try {
			kafkaProducer.send(new ProducerRecord<String, String>(logTopic,  Utils.parseObjectToJson(KafkaUtil.getEvent(operation, message))));
		}catch(Exception exception) {
			log.error("[ERROR] excecute APIException. Cause: "+exception.getCause()+", message: "+exception.getMessage()+".");
			throw new APIException(IAPIException.API_EXCEPTION_CODE_LOG_TOPIC_ERROR, IAPIException.API_EXCEPTION_MESSAGE_LOG_TOPIC_ERROR);
		}
		
		log.info("[INFO] method sendEvent with uuid :"+operation.getUuid()+".Finish at: "+Utils.getCurrentTimeStamp());
	}
}
