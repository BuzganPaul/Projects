package com.SDAssignment1.entities;

import com.SDAssignment1.entities.utils.Gender;
import com.SDAssignment1.entities.utils.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Person {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;


    @Column(nullable = false)
    @Size(min = 5, max = 30)
    private String username;

    @Column(nullable = false)
    @Size(min = 5, max = 30)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(nullable = false)
    @Size(min = 5, max = 30)
    @Pattern(regexp="^[A-Za-z]+$")
    private String name;

    @Column(nullable = false,
            columnDefinition = "DATETIME")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @Size(min = 5, max = 50)
    private String address;

}
