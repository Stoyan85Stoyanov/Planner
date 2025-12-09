package bg.softuni.planner.init;


import bg.softuni.planner.model.entity.Priority;
import bg.softuni.planner.model.entity.PriorityName;
import bg.softuni.planner.repository.PriorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class InitService implements CommandLineRunner {


    private final Map<PriorityName, String> careerInformation = Map.of(
            PriorityName.URGENT, "A An urgent problem that blocks the system use until the issue is resolved.",
            PriorityName.IMPORTANT, "A core functionality that your product is explicitly supposed to perform is compromised.",
            PriorityName.LOW, "Should be fixed if time permits but can be postponed."
    );

    private final PriorityRepository priorityRepository;

    public InitService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }


    @Override
    public void run(String... args) {

        long count = this.priorityRepository.count();

        if (count > 0) {
            return;
        }

        List<Priority> toInsert = Arrays.stream(PriorityName.values())
                .map(name -> new Priority(name, careerInformation.get(name))).toList();

        this.priorityRepository.saveAll(toInsert);
    }
}
