package com.iths.airtravels.util;

import com.iths.airtravels.entity.*;
import com.iths.airtravels.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataGenerator {

    @Bean
    CommandLineRunner initDatabase(CategoriesRepository categoriesRepository,
                                   FlightRepository flightRepository,
                                   HotelRepository hotelRepository,
                                   LocationRepository locationRepository,
                                   LuggageRepository luggageRepository,
                                   RoleRepository roleRepository,
                                   TicketRepository ticketRepository,
                                   UsersRepository usersRepository) {
        return args -> {
            if (categoriesRepository.count() == 0 && flightRepository.count() == 0 && hotelRepository.count() == 0 && locationRepository.count() == 0 && luggageRepository.count() == 0 && roleRepository.count() == 0 && ticketRepository.count() == 0 && usersRepository.count() == 0) {
                var role1 = new Roles(1L, "ROLE_USER");
                var role2 = new Roles(2L, "ROLE_ADMIN");
                var role3 = new Roles(3L, "ROLE_MODERATOR");

                roleRepository.save(role1);
                roleRepository.save(role2);
                roleRepository.save(role3);

                var category1 = new Categories(4L, "Strand");
                var category2 = new Categories(5L, "Gym");
                var category3 = new Categories(6L, "Pool");
                var category4 = new Categories(7L, "Wi-fi");
                var category5 = new Categories(8L, "Husdjur");
                var category6 = new Categories(9L, "Frukost");
                var category7 = new Categories(10L, "All-inclusive");
                var category8 = new Categories(11L, "Parkering");

                categoriesRepository.save(category1);
                categoriesRepository.save(category2);
                categoriesRepository.save(category3);
                categoriesRepository.save(category4);
                categoriesRepository.save(category5);
                categoriesRepository.save(category6);
                categoriesRepository.save(category7);
                categoriesRepository.save(category8);

                List<Roles> adminrole = new ArrayList<>();
                adminrole.add(role2);
                var user1 = new Users(12L,"admin@gmail.com","$2y$12$opKb/D12lAgCeToOg0oPg.ZFUXBjN7RNBOV3FW/r9aSN1s49E5s2W","Admin Admin","default.png",adminrole);
                List<Roles> moderator = new ArrayList<>();
                moderator.add(role3);
                var user2 = new Users(13L,"moderator@gmail.com","$2y$12$opKb/D12lAgCeToOg0oPg.ZFUXBjN7RNBOV3FW/r9aSN1s49E5s2W","Moderator Moderator","default.png",moderator);
                List<Roles> userrole = new ArrayList<>();
                userrole.add(role1);
                var user3 = new Users(14L,"anton@gmail.com","$2y$12$opKb/D12lAgCeToOg0oPg.ZFUXBjN7RNBOV3FW/r9aSN1s49E5s2W","Anton Johansson","default.png",userrole);

                usersRepository.save(user1);
                usersRepository.save(user2);
                usersRepository.save(user3);

                var location1 = new Location("Stockholm","Sverige");
                var location2 = new Location("Madrid","Spanien");
                var location3 = new Location("Göteborg","Sverige");
                var location4 = new Location("Paris","Frankrike");
                locationRepository.save(location1);
                locationRepository.save(location2);
                locationRepository.save(location3);
                locationRepository.save(location4);


            }
        };

    }
}