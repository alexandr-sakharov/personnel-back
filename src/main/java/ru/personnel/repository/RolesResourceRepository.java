package ru.personnel.repository;

import org.springframework.data.repository.CrudRepository;
import ru.personnel.entity.RolesResourceEntity;

import java.util.List;

public interface RolesResourceRepository extends CrudRepository<RolesResourceEntity, Integer> {

    /**
     * Получение ресурсов по id роли
     *
     * @param roleId id роли
     */
    List<RolesResourceEntity> findAllByRole_Id(Integer roleId);
}
