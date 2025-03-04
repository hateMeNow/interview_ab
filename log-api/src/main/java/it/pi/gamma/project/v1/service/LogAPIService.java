package it.pi.gamma.project.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.pi.gamma.project.model.GPLogEvent;
import it.pi.gamma.project.service.IService;
import it.pi.gamma.project.util.LogUtils;
import it.pi.gamma.project.util.Utils;
import it.pi.gamma.project.v1.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogAPIService implements IService{

	@Autowired
	private LogRepository logRepository;
	
	public void writeLog(GPLogEvent logEvent){
		
		log.info("[INFO] Entering method: writeLog. Params [uuid: "+logEvent.getUuid()+"]. Start at: "+Utils.getCurrentTimeStamp());

		try {
			logRepository.insert(LogUtils.parseGPLog(logEvent));
		}catch(Exception e) {
			log.error("[ERROR] method insert, uuid: "+logEvent.getUuid()+". Cause: "+e.getCause()+", message: "+e.getMessage()+".");
		}		
		log.info("[INFO] method writeLog with uuid :"+logEvent.getUuid()+".Finish at: "+Utils.getCurrentTimeStamp());
		
	}
	
}
