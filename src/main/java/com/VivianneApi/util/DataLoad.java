package com.VivianneApi.util;

import com.VivianneApi.entity.Address;
import com.VivianneApi.entity.Member;
import com.VivianneApi.repository.AddressRepository;
import com.VivianneApi.repository.AppUserRepository;
import com.VivianneApi.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoad {

    @Bean
    CommandLineRunner loadData (MemberRepository memberRepo, AddressRepository addressRepo) {
        return args -> {
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

            memberRepo.save(new Member("Vivianne", "Eriksson", Kungsgatan7
            ,"vivi@email.se", "0707000000", "920308"));
            memberRepo.save(new Member("Skye", "Patrol", Simtuna4
            , "skye@gmail.com", "087749898", "20000102"));
            memberRepo.save(new Member("Laila", "Bagge", Stockholmsvagen38
            , "lailaBagge@rixfm.se", "0708995684", "19710318"));
            memberRepo.save(new Member("Tomas", "Wigell", Sundsvallsgatan1
            , "tomas.wigell@gmail.com", "0769365738", "19700404"));
            memberRepo.save(new Member("Alex", "Ryder", Drottgatan111
            , "rydercool@hotmail.com", "0103555372", "20150824"));
        };
    }
}
