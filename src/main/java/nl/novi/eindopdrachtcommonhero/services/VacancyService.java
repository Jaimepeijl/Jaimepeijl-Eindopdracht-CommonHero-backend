package nl.novi.eindopdrachtcommonhero.services;

import nl.novi.eindopdrachtcommonhero.exceptions.VacancyNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.Vacancy;
import nl.novi.eindopdrachtcommonhero.repositories.FileUploadRepository;
import nl.novi.eindopdrachtcommonhero.repositories.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyService {

    private VacancyRepository vacancyRepository;
    private FileUploadRepository uploadRepository;

    @Autowired
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
    public Vacancy createVacancy (Vacancy vacancy){
        vacancyRepository.save(vacancy);
        return this.createVacancy(vacancy);
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
