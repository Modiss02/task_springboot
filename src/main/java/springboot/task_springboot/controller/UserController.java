package springboot.task_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springboot.task_springboot.entity.User;
import springboot.task_springboot.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showAllUsers(ModelMap model) {
        List<User> user = userService.findAll();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("user/add-page")
    public String addUserPage(@ModelAttribute User user) {
        return "user-add";
    }

    @PostMapping("user/save")
    public String create(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/api/user";
    }

    @GetMapping("user/update-page/{id}")
    public String updateUserPage(ModelMap model, @PathVariable("id") long id) {
        User user = userService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
        }

        return "user-update";
    }

    @PostMapping("user/update")
    public String update(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/api/user";
    }

    @PostMapping ("user/delete/{id}")
    public String delete(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/api/user";
    }




}