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
public class MedicationPlan {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Size(min = 5, max = 30)
    @Column(nullable = false)
    private String medicalCondition;

    @OneToOne(cascade = CascadeType.ALL,
            orphanRemoval = false)
    private Patient patient;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<MedicationPrescription> medicationPrescriptions = new ArrayList<>();

}
