package com.clinicsystem.patient.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private UUID doctorId;
    private Date appointmentDate;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    private String notes;
}

enum AppointmentStatus {
    SCHEDULED, COMPLETED, CANCELED
}
