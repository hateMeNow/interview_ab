package it.pi.gamma.project.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.pi.gamma.project.v1.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {


}
