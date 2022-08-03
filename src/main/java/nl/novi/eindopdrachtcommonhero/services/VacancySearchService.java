package nl.novi.eindopdrachtcommonhero.services;

import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancySearchData;
import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancySearchRequest;
import nl.novi.eindopdrachtcommonhero.exceptions.BadRequestException;
import nl.novi.eindopdrachtcommonhero.exceptions.VacancyNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.VacancySearch;
import nl.novi.eindopdrachtcommonhero.repositories.FileUploadRepository;
import nl.novi.eindopdrachtcommonhero.repositories.VacancySearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacancySearchService {

    private final VacancySearchRepository vacancySearchRepository;
    private final FileUploadRepository uploadRepository;

    public VacancySearchService(VacancySearchRepository vacancySearchRepository, FileUploadRepository uploadRepository) {
        this.vacancySearchRepository = vacancySearchRepository;
        this.uploadRepository = uploadRepository;
    }

    public List<VacancySearch> getSearchVacancies(){
        return vacancySearchRepository.findAll();
    }

    public VacancySearch getSearchVacancy(Long id) {
        return this.vacancySearchRepository.findById(id)
                .orElseThrow(VacancyNotFoundException::new);
    }

    public void deleteSearchVacancy(Long id) {
        if(!vacancySearchRepository.existsById(id)){
            throw new VacancyNotFoundException();
        }
        vacancySearchRepository.deleteById(id);
    }

    public VacancySearch createSearchVacancy (VacancySearchRequest vacancySearchRequest){
        try{
            VacancySearch vacancySearch = toVacancySearch(vacancySearchRequest);
            return vacancySearchRepository.save(vacancySearch);
        } catch (Exception ex) {
            throw new BadRequestException("Kan vacature niet aanmaken");
        }
    }

    public VacancySearchData updateVacancySearch(Long id, VacancySearchRequest newVacancy) {
        if (!vacancySearchRepository.existsById(id)) throw new VacancyNotFoundException();

        VacancySearch vacancySearch = this.getSearchVacancy(id);

        vacancySearch.setTitle(newVacancy.title);
        vacancySearch.setHours(newVacancy.hours);
        vacancySearch.setDescription(newVacancy.description);
        vacancySearch.setCity(newVacancy.city);
        vacancySearch.setRepeats(newVacancy.repeats);
        vacancySearch.setDate(newVacancy.date);

        this.vacancySearchRepository.save(vacancySearch);
        return this.createVacancySearchData(vacancySearch);
    }

    public VacancySearch toVacancySearch(VacancySearchRequest vacancySearchRequest){
        var vacancy = new VacancySearch();

        vacancy.setId(vacancySearchRequest.getId());
        vacancy.setPublisher(vacancySearchRequest.getPublisher());
        vacancy.setTitle(vacancySearchRequest.getTitle());
        vacancy.setHours(vacancySearchRequest.getHours());
        vacancy.setDescription(vacancySearchRequest.getDescription());
        vacancy.setCity(vacancySearchRequest.getCity());
        vacancy.setRepeats(vacancySearchRequest.getRepeats());
        vacancy.setDate(vacancySearchRequest.getDate());

        return vacancy;
    }
    public VacancySearchData createVacancySearchData(VacancySearch vacancySearch) {
        return new VacancySearchData(
                vacancySearch.getPublisher(),
                vacancySearch.getTitle(),
                vacancySearch.getHours(),
                vacancySearch.getDescription(),
                vacancySearch.getCity(),
                vacancySearch.getRepeats(),
                vacancySearch.getDate()
        );
    }
    public void assignPhotoToVacancySearch(String fileName, Long id) {
        Optional<VacancySearch> optionalVacancy = vacancySearchRepository.findById(id);
        Optional<FileUploadResponse> fileUploadResponse = uploadRepository.findByFileName(fileName);
        if (optionalVacancy.isPresent() && fileUploadResponse.isPresent()){
            FileUploadResponse photo = fileUploadResponse.get();
            VacancySearch vacancySearch = optionalVacancy.get();
            vacancySearch.setFile(photo);
            vacancySearchRepository.save(vacancySearch);
        }
    }
}
