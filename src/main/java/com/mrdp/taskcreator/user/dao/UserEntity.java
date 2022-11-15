package com.mrdp.taskcreator.user.dao;

import com.mrdp.taskcreator.role.RoleEntity;
import com.mrdp.taskcreator.task.dao.Task;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;

    private String password;

    private String email;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToOne
    private RoleEntity role;
}
