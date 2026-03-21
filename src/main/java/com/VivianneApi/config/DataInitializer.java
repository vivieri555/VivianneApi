package com.VivianneApi.config;

import com.VivianneApi.entity.Address;
import com.VivianneApi.entity.Member;
import com.VivianneApi.repository.AddressRepository;
import com.VivianneApi.repository.AppUserRepository;
import com.VivianneApi.repository.MemberRepository;
import com.VivianneApi.security.AppUser;
import com.VivianneApi.security.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

import static com.VivianneApi.security.Role.USER;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(AppUserRepository appUserRepo,
                               PasswordEncoder passwordEncoder, AddressRepository addressRepo,
                               MemberRepository memberRepo) {
        return args -> {
            if (appUserRepo.count() == 0) {
                AppUser superAdmin = new AppUser(
                        "superadmin",
//                        "superadmin",
                        passwordEncoder.encode("superadmin"),
                        Set.of(Role.ADMIN, Role.USER)
                );

                AppUser admin1 = new AppUser(
                        "admin1",
//                        "admin1",
                        passwordEncoder.encode("admin1"),
                        Set.of(Role.ADMIN)
                );
                appUserRepo.saveAll(List.of(admin1, superAdmin));
                System.out.println("admin1 och superadmin skapat");

                Address Sundsvallsgatan1 = new Address("Sundsvallsgatan 1", 85474, "Sundsvall");
                Address Stockholmsvagen38 = new Address("Stockholmsvägen 38", 11715, "Stockholm");
                Address Simtuna4 = new Address("Simtuna 4", 74972, "Enköping");
                Address Kungsgatan7 = new Address("Kungsgatan 7", 11731, "Stockholm");
                Address Drottgatan111 = new Address("Drottgatan 111", 24321, "Malmö");
                addressRepo.save(Sundsvallsgatan1);
                addressRepo.save(Stockholmsvagen38);
                addressRepo.save(Simtuna4);
                addressRepo.save(Kungsgatan7);
                addressRepo.save(Drottgatan111);

                Member m1 = new Member("Vivianne", "Eriksson", Kungsgatan7
                        , "vivi@email.se", "0707000000", "920308");
                Member m2 = new Member("Skye", "Patrol", Simtuna4
                        , "skye@gmail.com", "087749898", "20000102");
                Member m3 = memberRepo.save(new Member("Laila", "Bagge", Stockholmsvagen38
                        , "lailaBagge@rixfm.se", "0708995684", "19710318"));
                Member m4 = memberRepo.save(new Member("Tomas", "Wigell", Sundsvallsgatan1
                        , "tomas.wigell@gmail.com", "0769365738", "19700404"));
                Member m5 = memberRepo.save(new Member("Alex", "Ryder", Drottgatan111
                        , "rydercool@hotmail.com", "0103555372", "20150824"));

                memberRepo.saveAll(List.of(m1, m2, m3, m4, m5));
                memberRepo.flush();

                AppUser user = new AppUser("vivi@email.se",
                        passwordEncoder.encode("password"),
                        Set.of(USER), m1);
                AppUser user2 = new AppUser("skye@gmail.com",
                        passwordEncoder.encode("password"),
                        Set.of(USER), m2);
                AppUser user3 = new AppUser("lailaBagge@rixfm.se", passwordEncoder.encode("password"),
                        Set.of(USER), m3);
                AppUser user4 = new AppUser("tomas.wigell@gmail.com", passwordEncoder.encode("password"),
                        Set.of(USER), m4);
                AppUser user5 = new AppUser("rydercool@hotmail.com", passwordEncoder.encode("password"),
                        Set.of(USER), m5);

                appUserRepo.saveAll(List.of(user, user2, user3, user4, user5));
            }
        };
    }
}
