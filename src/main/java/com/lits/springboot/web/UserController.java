package com.lits.springboot.web;

import com.lits.springboot.dtos.FootballTeamDto;
import com.lits.springboot.dtos.UserDto;
import com.lits.springboot.entity.User;
import com.lits.springboot.service.impl.UserService;
import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        User registered = new User();
//        if (!result.hasErrors()) {
//            registered = service.registerNewUserAccount(accountDto);
//        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        }
        else {
            return new ModelAndView("successRegister", "user", accountDto);
        }
    }
//
//    @GetMapping(value = "/allUsers")
//    public List<UserDto> getAllUsers(){
//        return service.getAll();
//    }
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteMethod(HttpServletRequest request) {
        request.isUserInRole("ROLE_ADMIN");
    }

    @PostMapping(value = "/save")
    public UserDto saveUser(@RequestBody UserDto userDto){
        return service.registerNewUserAccount(userDto);
    }


//    @Controller
//    @RequestMapping("/login")
//    public class HelloWorldController {
//        @GetMapping
//        String getView(Model model) {
//            model.addAttribute("msg", "Hello there, This message has been injected from the controller method");
//            return "login";
//        }
//    }

    @GetMapping(value ="/admin")
    public String adm(){
        return "admin";
    }





//    private User createUserAccount(UserDto accountDto, BindingResult result) {
//        User registered = service.registerNewUserAccount(accountDto);
//        return registered;
//    }
}
