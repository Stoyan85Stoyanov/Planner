package bg.softuni.planner.service;

import bg.softuni.planner.config.UserSession;
import bg.softuni.planner.dto.AddTaskDto;
import bg.softuni.planner.dto.TaskHomeViewModel;
import bg.softuni.planner.dto.TaskInfoDto;
import bg.softuni.planner.model.entity.Priority;
import bg.softuni.planner.model.entity.PriorityName;
import bg.softuni.planner.model.entity.Task;
import bg.softuni.planner.model.entity.User;
import bg.softuni.planner.repository.PriorityRepository;
import bg.softuni.planner.repository.TaskRepository;
import bg.softuni.planner.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    private final UserSession userSession;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;

    public TaskService(UserSession userSession, UserRepository userRepository, TaskRepository taskRepository, PriorityRepository priorityRepository) {
        this.userSession = userSession;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
    }

    public boolean create(AddTaskDto data) {

        if (!userSession.isLoggedIn()) {
            return false;
        }

        Optional<User> byId = userRepository.findById(userSession.id());

        if (byId.isEmpty()) {
            return false;
        }

        Optional<Priority> byName = priorityRepository.findByPriorityName(PriorityName.valueOf(data.getPriority()));

        if (byName.isEmpty()) {
            return false;
        }

        Task task = new Task();
        task.setDescription(data.getDescription());
        task.setDueDate(data.getDueDate());
        task.setPriority(byName.get());
        task.setUser(byId.get());

        taskRepository.save(task);
        return true;
    }

    public Map<PriorityName, List<Task>> findTaskByPriority() {
        Map<PriorityName, List<Task>> result = new HashMap<>();

        List<Priority> allPriorities = priorityRepository.findAll();

        for (Priority priority : allPriorities) {
            List<Task> tasks = taskRepository.findTaskByPriority(priority);
            result.put(priority.getPriorityName(), tasks);
        }
        return result;
    }


    public void delete(Long id) {
        taskRepository.deleteById(id);
    }


    public void  assign(Long id, String username) {
         Optional<Task> assignTask = taskRepository.findById(id);

         if (assignTask.isPresent()) {
             Task task = assignTask.get();

             if (username == null) {
                 task.setUser(null);

             }else {
                 User user = userRepository.findByUsername(username);
                 task.setUser(user);
             }
             taskRepository.save(task);
         }
    }

    public TaskHomeViewModel getHomeViewData(String username) {
        User user = userRepository.findByUsername(username);

       List<TaskInfoDto> assignedTasks = taskRepository.findByUser(user).stream()
               .map(TaskInfoDto::createFromTask).toList();

       List<TaskInfoDto> availableTasks = taskRepository.findAllTasks().stream()
               .map(TaskInfoDto::createFromTask).toList();;

       return new TaskHomeViewModel(assignedTasks, availableTasks);
    }
}
