package com.VivianneApi.util;

import com.VivianneApi.entity.Member;
import com.VivianneApi.repository.AppUserRepository;
import com.VivianneApi.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoad {

    @Bean
    CommandLineRunner loadData (MemberRepository memberRepo, AppUserRepository appUserRepo) {
        return args -> {
            memberRepo.save(new Member("Vivianne", "Eriksson", "adress"
            ,"vivi@email.se", "0707000000", "920308"));
            memberRepo.save(new Member("Skye", "Patrol", "adress"
            , "skye@gmail.com", "087749898", "20000102"));
            memberRepo.save(new Member("Laila", "Bagge", "adress"
            , "lailaBagge@rixfm.se", "0708995684", "19710318"));
            memberRepo.save(new Member("Tomas", "Wigell", "Sundsvall"
            , "tomas.wigell@gmail.com", "0769365738", "19700404"));
            memberRepo.save(new Member("Alex", "Ryder", "adress"
            , "rydercool@hotmail.com", "0103555372", "20150824"));
        };
    }
}
