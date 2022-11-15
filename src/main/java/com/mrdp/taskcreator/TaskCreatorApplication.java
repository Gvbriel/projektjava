package com.mrdp.taskcreator;

import com.mrdp.taskcreator.role.RoleEntity;
import com.mrdp.taskcreator.role.RoleRepository;
import com.mrdp.taskcreator.user.dao.UserEntity;
import com.mrdp.taskcreator.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TaskCreatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskCreatorApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) throws Exception {
        return args -> {
            if(roleRepository.findRoleEntityByName("ROLE_ADMIN").isEmpty()){
                RoleEntity role1 = new RoleEntity();
                role1.setName("ROLE_ADMIN");
                roleRepository.save(role1);

                RoleEntity role2 = new RoleEntity();
                role2.setName("ROLE_USER");
                roleRepository.save(role2);

                UserEntity admin = new UserEntity();
                admin.setUsername("admin");
                admin.setEmail("admin@admin.com");
                admin.setPassword(passwordEncoder.encode("secret"));
                admin.setRole(role1);
                userRepository.save(admin);
            }
        };
    }
}
