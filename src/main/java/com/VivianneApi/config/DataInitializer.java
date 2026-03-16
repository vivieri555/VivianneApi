package com.VivianneApi.config;

import com.VivianneApi.repository.AppUserRepository;
import com.VivianneApi.security.AppUser;
import com.VivianneApi.security.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(AppUserRepository appUserRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if (appUserRepo.count() == 0) {
                AppUser superAdmin = new AppUser(
                        "superadmin",
                        passwordEncoder.encode("superadmin"),
                        Set.of(Role.ADMIN, Role.USER)
                );

                AppUser admin1 = new AppUser(
                        "admin1",
                        passwordEncoder.encode("admin1"),
                        Set.of(Role.ADMIN)
                );
                appUserRepo.saveAll(List.of(admin1, superAdmin));
                System.out.println("admin1 och superadmin skapat");
            }
        };
    }
}
