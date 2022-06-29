package nl.novi.eindopdrachtcommonhero.services;

import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancyData;
import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancyRequest;
import nl.novi.eindopdrachtcommonhero.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtcommonhero.exceptions.VacancyNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.Vacancy;
import nl.novi.eindopdrachtcommonhero.repositories.FileUploadRepository;
import nl.novi.eindopdrachtcommonhero.repositories.VacancyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final FileUploadRepository uploadRepository;

    public VacancyService(VacancyRepository vacancyRepository, FileUploadRepository uploadRepository) {
        this.vacancyRepository = vacancyRepository;
        this.uploadRepository = uploadRepository;
    }

    public List<Vacancy> getVacancies(){
        return vacancyRepository.findAll();
    }
    public Vacancy getVacancy(Long id) {
        return this.vacancyRepository.findById(id)
                .orElseThrow(VacancyNotFoundException::new);
    }

    public void deleteVacancy(Long id) {
        if(!vacancyRepository.existsById(id)){
            throw new VacancyNotFoundException();
        }
        vacancyRepository.deleteById(id);
    }
    public VacancyData createVacancy (VacancyRequest vacancyRequest){
        Vacancy vacancy = toVacancy(vacancyRequest);
        vacancyRepository.save(vacancy);
        return this.createVacancyData(vacancy);
    }
    public VacancyData updateVacancy(Long id, VacancyRequest newVacancy) {
        if (!vacancyRepository.existsById(id)) throw new RecordNotFoundException();

        Vacancy vacancy = this.getVacancy(id);

//        vacancy.setPublisher(newVacancy.publisher);
        vacancy.setHours(newVacancy.hours);
        vacancy.setDescription(newVacancy.description);

        this.vacancyRepository.save(vacancy);
        return this.createVacancyData(vacancy);
    }

    public Vacancy toVacancy(VacancyRequest vacancyRequest){
        var vacancy = new Vacancy();

        vacancy.setId(vacancyRequest.getId());
//        vacancy.setPublisher(vacancyRequest.getPublisher());
        vacancy.setSearchOrOffer(vacancyRequest.isSearchOrOffer());
        vacancy.setDescription(vacancyRequest.getDescription());

        return vacancy;
    }

    public VacancyData createVacancyData(Vacancy vacancy) {
        return new VacancyData(
                vacancy.getPublisher(),
                vacancy.getTitle(),
                vacancy.getHours(),
                vacancy.isSearchOrOffer(),
                vacancy.getDescription()
        );
    }

    public void assignPhotoToVacancy(String fileName, Long id) {
        Optional<Vacancy> optionalVacancy = vacancyRepository.findById(id);
        Optional<FileUploadResponse> fileUploadResponse = uploadRepository.findByFileName(fileName);
        if (optionalVacancy.isPresent() && fileUploadResponse.isPresent()){
            FileUploadResponse photo = fileUploadResponse.get();
            Vacancy vacancy = optionalVacancy.get();
            vacancy.setFile(photo);
            vacancyRepository.save(vacancy);
        }
    }
}
