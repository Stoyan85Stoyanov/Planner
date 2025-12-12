package bg.softuni.planner.repository;

import bg.softuni.planner.model.entity.Priority;
import bg.softuni.planner.model.entity.PriorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

    Optional<Priority> findByPriorityName(PriorityName name);
}
