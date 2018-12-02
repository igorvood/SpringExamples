package ru.vood.spring.restFullStack.repository.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vood.spring.restFullStack.entity.UsrEntity;

public interface UsrJpaRepository extends JpaRepository<UsrEntity, Long>, JpaSpecificationExecutor<UsrEntity> {
}
