package com.SDAssignment1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MedicationPrescription {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL,
            orphanRemoval = false)
    private Medication medication;

    @Column(nullable = false,
            columnDefinition = "DATETIME")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(nullable = false,
            columnDefinition = "DATETIME")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(nullable = false)
    private int timesADay;

}
