package emsi.ma.springmvc2.sec.service;

import emsi.ma.springmvc2.sec.entites.AppRole;
import emsi.ma.springmvc2.sec.entites.AppUser;

public interface SecurityService {
    AppUser saveUser(String username, String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUserName(String username);
}
