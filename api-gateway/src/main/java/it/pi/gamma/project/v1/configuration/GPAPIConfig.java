package it.pi.gamma.project.v1.configuration;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GPAPIConfig {

	@Value("${spring.kafka.producer.bootstrap-servers}")
	private String bootstrapServer;
	
	@Value("${spring.kafka.producer.key-serializer}")
	private String keySerializer;
	
	@Value("${spring.kafka.producer..value-serializer}")
	private String valueSerializer;
	
	@Bean
    public KafkaProducer<String,String> kafkaProducer() {
    	
		Properties properties = new Properties();
    	
    	properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    	
    	properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
    	
    	properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
		
		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
		
		return kafkaProducer;
    }
}
