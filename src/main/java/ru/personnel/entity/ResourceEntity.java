package ru.personnel.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "acl_resources")
public class ResourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "resource_code", nullable = false, length = 128)
    private String resourceCode;

    @Column(name = "title")
    private String title;
}