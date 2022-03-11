package org.project.TestOffer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.project.TestOffer.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
