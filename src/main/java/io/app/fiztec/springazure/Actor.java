package io.app.fiztec.springazure;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @Column(name = "actor_id")
    @GeneratedValue(generator = "actor_generator")
    @SequenceGenerator(name = "actor_generator", initialValue = 10)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String last_name;


}