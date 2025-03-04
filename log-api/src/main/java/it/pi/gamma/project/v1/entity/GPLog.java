package it.pi.gamma.project.v1.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Document(collection = "log")
public class GPLog{
	
    @Id
    private UUID log_id;

    @Field(name = "ip")
    private String ip;

    @Indexed(name="username", unique = true)
    private String username;

    @Field(name = "typeEvent")
    private String typeEvent;
    
    @Field(name = "description")
    private String description;
    
    @Field(name = "message")
    private String message;
    
    @Field(name = "date")
    private String date;

}