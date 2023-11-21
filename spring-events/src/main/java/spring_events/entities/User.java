package spring_events.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @Column(name = "id")
    Long userId;

    @Column(name = "name")
    String name;

    @Column(name = "age")
    Integer age;

    @Column(name = "create_date")
    Date createDate;
}
