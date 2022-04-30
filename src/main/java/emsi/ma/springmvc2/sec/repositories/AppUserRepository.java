package emsi.ma.springmvc2.sec.repositories;


import emsi.ma.springmvc2.sec.entites.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
