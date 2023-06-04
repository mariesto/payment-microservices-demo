package com.mariesto.userservice.persistence.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.mariesto.userservice.persistence.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByUserId(final String userId);

}
