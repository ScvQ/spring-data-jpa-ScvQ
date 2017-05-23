package cn.spiderpig.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.spiderpig.demo.domain.User;

public interface UserJpaRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

}
