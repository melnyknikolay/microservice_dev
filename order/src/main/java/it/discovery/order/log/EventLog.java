package it.discovery.order.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Table
@Entity
public class EventLog {
    @Id
    @GeneratedValue(generator="log_seq")
    @SequenceGenerator(name="log_seq",sequenceName="LOG_SEQ")
    private int id;

    private Instant timestamp;

    private String type;

    private int entityId;

    private String body;

    public EventLog(String type, int entityId, String body) {
        this.type = type;
        this.entityId = entityId;
        this.body = body;
    }
}
