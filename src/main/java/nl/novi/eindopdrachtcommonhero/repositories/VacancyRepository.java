package nl.novi.eindopdrachtcommonhero.repositories;

import nl.novi.eindopdrachtcommonhero.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}
