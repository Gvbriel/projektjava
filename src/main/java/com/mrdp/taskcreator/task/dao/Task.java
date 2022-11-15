package com.mrdp.taskcreator.task.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrdp.taskcreator.user.dao.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private boolean isFinished;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private UserEntity owner;
}
