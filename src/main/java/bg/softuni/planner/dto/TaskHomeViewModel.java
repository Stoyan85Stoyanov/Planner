package bg.softuni.planner.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskHomeViewModel {

    private List<TaskInfoDto> assignedTaskToMe;

    private List<TaskInfoDto> allAvailableTasks;
}
