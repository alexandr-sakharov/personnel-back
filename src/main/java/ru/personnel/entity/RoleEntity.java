package ru.personnel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "acl_roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status_code", nullable = false, length = 128)
    private String statusCode;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "role_name")
    private String roleName;

    // только одна роль может иметь флаг is_default 1
    @Column(name = "is_default")
    private Integer isDefault;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = RolesResourceEntity.class)
    @JoinColumn(referencedColumnName = "id", name = "role_id")
    private List<RolesResourceEntity> rolesResourceList;
}
