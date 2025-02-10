package com.isaquesoares.workshopmongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.isaquesoares.workshopmongo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ 'email' : { $regex: ?0, $options: 'i' } }") 
    Optional<User> findByEmail(String email);
}
