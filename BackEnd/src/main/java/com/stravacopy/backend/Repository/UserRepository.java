package com.stravacopy.backend.Repository;

import com.stravacopy.backend.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
    public User findById(String id);
    public User findByName(String name);
}
