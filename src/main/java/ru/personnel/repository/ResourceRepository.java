package ru.personnel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.personnel.entity.ResourceEntity;
import ru.personnel.entity.RolesResourceEntity;

import java.util.List;

public interface ResourceRepository extends CrudRepository<ResourceEntity, Integer> {

    /**
     * Получение ресурсов пользователя
     *
     * @param id пользователя
     */
    @Query("select role.rolesResourceList from RoleEntity role where role in (select user from UserEntity user where user.id=:id)")
    List<RolesResourceEntity> findResourcesByUserId(Integer id);

}
