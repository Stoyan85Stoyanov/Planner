package bg.softuni.planner.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddTaskDto {


    @NotBlank
    @Size(min = 3, max = 50)
    private String description;

    @Future
    private LocalDate dueDate;

    @NotBlank
    private String priority;

    public AddTaskDto() {
    }


    public  String getDescription() {
        return description;
    }

    public void setDescription( String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

}
