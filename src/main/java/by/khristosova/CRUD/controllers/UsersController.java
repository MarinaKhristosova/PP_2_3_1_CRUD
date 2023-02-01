package by.khristosova.CRUD.controllers;

import by.khristosova.CRUD.models.User;
import by.khristosova.CRUD.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UsersController {

    public final UserServiceImpl service;

    @Autowired
    public UsersController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "users/allUsers";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", service.showUser(id));
        return "users/showUser";
    }

    @GetMapping("/new")
    public String getViewForNewUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        service.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String getViewForUpdateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", service.showUser(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        service.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        service.deleteUser(id);
        return "redirect:/users";
    }
}
