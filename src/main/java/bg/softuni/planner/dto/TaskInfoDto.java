package bg.softuni.planner.dto;

import bg.softuni.planner.model.entity.Priority;
import bg.softuni.planner.model.entity.Task;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskInfoDto {

    private long id;

    private LocalDate dueDate;

    private Priority priority;

    private String description;


    public TaskInfoDto(Task task) {
        this.id = task.getId();
        this.dueDate = task.getDueDate();
        this.priority = task.getPriority();
        this.description = task.getDescription();
    }


    public static TaskInfoDto createFromTask(Task task) {
        TaskInfoDto taskInfoDto = new TaskInfoDto(task);

        taskInfoDto.setId(task.getId());
        taskInfoDto.setDescription(task.getDescription());
        taskInfoDto.setDueDate(task.getDueDate());
        taskInfoDto.setPriority(task.getPriority());

        return taskInfoDto;
    }
}
