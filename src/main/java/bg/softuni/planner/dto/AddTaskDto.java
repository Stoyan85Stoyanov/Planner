package bg.softuni.planner.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskDto {


    @NotBlank
    @Size(min = 3, max = 50)
    private String description;

    @Future
    private LocalDate dueDate;

    @NotBlank
    private String priority;

}
