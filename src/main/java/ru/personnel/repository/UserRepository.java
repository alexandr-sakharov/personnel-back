package ru.personnel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.personnel.entity.UserEntity;

import java.util.Optional;



public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    /**
     * Поиск пользователя по имени (активных)
     *
     * @param username имя пользователя
     */
    Optional<UserEntity> findUserByUsername(@Param("username") String username);
}
