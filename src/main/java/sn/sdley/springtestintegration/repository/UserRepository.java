package sn.sdley.springtestintegration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.sdley.springtestintegration.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
