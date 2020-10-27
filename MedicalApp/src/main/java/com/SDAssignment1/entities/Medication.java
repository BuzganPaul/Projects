package com.SDAssignment1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Medication {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Size(min = 5, max = 30)
    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
                orphanRemoval = false)
    private List<SideEffect> sideEffects = new ArrayList<>();

    @Size(min = 5, max = 30)
    @Column(nullable = false)
    private String dosage;
}
