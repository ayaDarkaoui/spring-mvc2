package emsi.ma.springmvc2;

import emsi.ma.springmvc2.entites.Patient;
import emsi.ma.springmvc2.repositories.PatientRepositories;
import emsi.ma.springmvc2.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SpringMvc2Application {

    public static void main(String[] args ){
        SpringApplication.run(SpringMvc2Application.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(PatientRepositories patientRepository){
        return args -> {
            patientRepository.save(
                    new Patient(null,"Saad","mohamed",new Date(),false,112));
            patientRepository.save(
                    new Patient(null,"Aya","mohamed",new Date(),false,156));
            patientRepository.save(
                    new Patient(null,"Yassine","mohamed",new Date(),false,130));
            patientRepository.save(
                    new Patient(null,"Ahmed","mohamed",new Date(),false,400));
            patientRepository.findAll().forEach(
                    p-> System.out.println(p.getNom()));
        };
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveUser("mohamed","1234","1234");
            securityService.saveUser("yasmine","1234","1234");
            securityService.saveUser("hassan","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("mohamed","USER");
            securityService.addRoleToUser("mohamed","ADMIN");
            securityService.addRoleToUser("yasmine","USER");
            securityService.addRoleToUser("hassan","USER");
        };
    }
}
