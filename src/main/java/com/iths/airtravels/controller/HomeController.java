package com.iths.airtravels.controller;

import com.iths.airtravels.entity.Flight;
import com.iths.airtravels.entity.Hotel;
import com.iths.airtravels.entity.Location;
import com.iths.airtravels.entity.Users;
import com.iths.airtravels.service.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final IUsersService usersService;
    private final FlightService flightService;
    private final LocationService locationService;
    private final HotelService hotelService;

    @Value("${file.avatar.viewPath}")
    private String viewPath;

    @Value("${file.avatar.uploadPath}")
    private String uploadPath;

    @Value("${file.avatar.defaultPicture}")
    private String defaultPicture;

    public HomeController(UsersService usersService, FlightService flightService, LocationService locationService, HotelService hotelService) {
        this.usersService = usersService;
        this.flightService = flightService;
        this.locationService = locationService;
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("currentUser", getUserData());
        List<Flight> flight = flightService.findAllFlights();
        model.addAttribute("flight", flight);
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);

        return "home";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "403";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "login";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "profile";
    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("currentUser", getUserData());
        return "register";

    }

    @PostMapping("/register")
    public String toRegister(@RequestParam(name = "user_email") String email,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "re_user_password") String rePassword,
                             @RequestParam(name = "user_fullname") String fullName) {

        if (password.equals(rePassword)) {

            Users newUser = new Users();
            newUser.setFullName(fullName);
            newUser.setPassword(password);
            newUser.setEmail(email);

            if (usersService.creatUser(newUser) != null) {
                return "redirect:/register?success";
            }

        }
        return "redirect:/register?error";
    }

    @PostMapping("/uploadavatar")
    @PreAuthorize("isAuthenticated()")
    public String uploadAvatar(@RequestParam(name = "user_avatar") MultipartFile file) {

        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {


            try {
                Users currentUser = getUserData();
                String picName = DigestUtils.sha1Hex("avatar_" + currentUser.getId() + "_!Picture");

                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadPath + picName + ".jpg");
                Files.write(path, bytes);

                currentUser.setUserAvatar(picName);
                usersService.saveUser(currentUser);

                return "redirect:/profile?success";

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/";

    }
    @GetMapping("/buyflight/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MODERATOR')")
    public String buyFlight(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("currentUser", getUserData());
        Flight flight = flightService.findFlightById(id);
        model.addAttribute("flight", flight);

        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);


        return "buyflight";
    }



    @GetMapping(value = "/viewphoto/{url}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody byte[] viewProfilePhoto(@PathVariable(name = "url") String url) throws IOException{

        String pictureURL = viewPath+defaultPicture;

        if(url!=null&&!url.equals("null")){
            pictureURL = viewPath+url+".jpg";
        }

        InputStream in;

        try{

            ClassPathResource resource = new ClassPathResource(pictureURL);
            in = resource.getInputStream();

        }catch (Exception e){
            ClassPathResource resource = new ClassPathResource(viewPath+defaultPicture);
            in = resource.getInputStream();
            e.printStackTrace();
        }

        return IOUtils.toByteArray(in);
    }

    private Users getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User secUser = (User) authentication.getPrincipal();
            Users myUser = usersService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }

}
