package demo.jpa_mysql_postgres.mysql.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String email;

    @Column
    private String description;

    @Column
    private Date birthday;

    @Column
    private Boolean active;

    @Column(name = "create_date")
    private Date createDate;

}
