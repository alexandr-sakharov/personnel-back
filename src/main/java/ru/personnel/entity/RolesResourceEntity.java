package ru.personnel.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "acl_roles_resources")
public class RolesResourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resource_id", nullable = false, referencedColumnName = "id")
    private ResourceEntity resource;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
    private RoleEntity role;

    @Column(name = "access_kind", length = 2)
    private String accessKind;
}
