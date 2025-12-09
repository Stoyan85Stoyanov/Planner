package bg.softuni.planner.controller;

import bg.softuni.planner.config.UserSession;
import bg.softuni.planner.dto.TaskHomeViewModel;
import bg.softuni.planner.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private final UserSession userSession;
    private final TaskService taskService;


    public HomeController(UserSession userSession, TaskService taskService) {
        this.userSession = userSession;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index() {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {

        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        TaskHomeViewModel viewModel = taskService.getHomeViewData(userSession.username());
        model.addAttribute("viewModel", viewModel);

        return "home";
    }
}
