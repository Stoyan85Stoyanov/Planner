package bg.softuni.planner.controller;

import bg.softuni.planner.config.UserSession;
import bg.softuni.planner.dto.AddTaskDto;
import bg.softuni.planner.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskController {

    private final UserSession userSession;
    private final TaskService taskService;


    public TaskController(UserSession userSession, TaskService taskService) {
        this.userSession = userSession;
        this.taskService = taskService;

    }

    @ModelAttribute("taskData")
    public AddTaskDto taskData() {
        return new AddTaskDto();
    }

    @GetMapping("/task-add")
    public String addTask() {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        return "task-add";
    }

    @PostMapping("/task-add")
    public String doAddTask(@Valid AddTaskDto data,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
    ) {

        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskData", bindingResult);

            return "redirect:/task-add";
        }

        boolean success = taskService.create(data);

        if (!success) {
            redirectAttributes.addFlashAttribute("taskData", data);

            return "redirect:/task-add";
        }
        return "redirect:/home";
    }
    // -----  task-add

//   @PostMapping("/add-to-assigned-to-me/{taskId}")
//     public String addToAssignedToMe(@PathVariable long taskId) {
//
//       if (!userSession.isLoggedIn()) {
//           return "redirect:/";
//       }
//
//       taskService.addToAssignedToMe(userSession.id(), taskId);
//
//       return "redirect:/home";
//   }
//
//
        // Delete button !!!
    @GetMapping("/tasks/remove/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/home";
    }

    @GetMapping("/tasks/return/{id}")
    public ModelAndView returnTask(@PathVariable("id") Long id) {
        taskService.assign(id, null);

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/tasks/assign/{id}")
    public ModelAndView assign(@PathVariable("id") Long id) {
        taskService.assign(id, userSession.username());

        return new ModelAndView("redirect:/home");
    }

}
