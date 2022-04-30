package emsi.ma.springmvc2.sec.repositories;

import emsi.ma.springmvc2.sec.entites.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
