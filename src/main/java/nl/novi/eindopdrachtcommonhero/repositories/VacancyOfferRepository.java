package nl.novi.eindopdrachtcommonhero.repositories;

import nl.novi.eindopdrachtcommonhero.models.VacancyOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VacancyOfferRepository extends JpaRepository<VacancyOffer, Long> {
}
