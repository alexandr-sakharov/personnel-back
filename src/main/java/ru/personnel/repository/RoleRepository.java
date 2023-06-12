package ru.personnel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.personnel.entity.RoleEntity;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {

    // Получение роли, которая присвоить пользователю по умолчанию
    @Query("select role from RoleEntity role where role.isDefault=1")
    Optional<RoleEntity> findByDefaultRole();
}
