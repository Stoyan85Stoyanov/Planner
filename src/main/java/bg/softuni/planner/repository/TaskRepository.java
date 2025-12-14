package bg.softuni.planner.repository;

import bg.softuni.planner.model.entity.Priority;
import bg.softuni.planner.model.entity.Task;
import bg.softuni.planner.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTaskByPriority(Priority priority);

    @Query("SELECT t FROM Task t")
    List<Task> findAllTasks();

    List<Task> findByUser(User user);
}
