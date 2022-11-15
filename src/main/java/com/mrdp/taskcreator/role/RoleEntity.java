package com.mrdp.taskcreator.role;

import com.mrdp.taskcreator.user.dao.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST}, mappedBy = "role")
    private Set<UserEntity> users = new HashSet<>();
}
