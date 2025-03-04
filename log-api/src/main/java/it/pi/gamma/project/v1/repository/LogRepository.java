package it.pi.gamma.project.v1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.pi.gamma.project.v1.entity.GPLog;

@Repository
public interface LogRepository extends MongoRepository<GPLog, Long> {

	
}
