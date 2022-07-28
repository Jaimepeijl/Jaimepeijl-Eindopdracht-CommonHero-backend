package nl.novi.eindopdrachtcommonhero.controllers;

import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancyOfferData;
import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancyOfferRequest;
import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancySearchData;
import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancySearchRequest;
import nl.novi.eindopdrachtcommonhero.exceptions.BadRequestException;
import nl.novi.eindopdrachtcommonhero.exceptions.VacancyNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.VacancyOffer;
import nl.novi.eindopdrachtcommonhero.models.VacancySearch;
import nl.novi.eindopdrachtcommonhero.services.VacancyOfferService;
import nl.novi.eindopdrachtcommonhero.services.VacancySearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/vacancies/search")
public class VacancySearchController {

    private final VacancySearchService vacancyService;
    private final PhotoController photoController;

    public VacancySearchController(VacancySearchService vacancyService, PhotoController photoController) {
        this.vacancyService = vacancyService;
        this.photoController = photoController;
    }

    @GetMapping
    @Transactional
    public List<VacancySearch> getSearchVacancies(){
        List<VacancySearch> vacancies;

        vacancies  = vacancyService.getSearchVacancies();

        return vacancies;
    }

    @PostMapping
    public VacancySearch createSearchVacancy(@RequestBody VacancySearchRequest vacancySearchRequest){
        try{
        VacancySearch vacancySearch = vacancyService.createSearchVacancy(vacancySearchRequest);
        return vacancySearch;
        } catch(BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public VacancySearch getSearchVacancy(@PathVariable Long id){
        try {
            return vacancyService.getSearchVacancy(id);
        } catch (VacancyNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public VacancySearchData updateVacancy(@RequestBody VacancySearchRequest vacancySearchRequest){
        try{
            return vacancyService.updateVacancySearch(vacancySearchRequest.id, vacancySearchRequest);
        } catch(VacancyNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteSearchVacancy(@PathVariable Long id){
        try{
            this.vacancyService.deleteSearchVacancy(id);
        } catch (VacancyNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/photo")
    public void assignPhotoToVacancySearch(@PathVariable("id") Long id, @RequestBody MultipartFile file){
        FileUploadResponse photo = photoController.singleFileUpload(file);

        vacancyService.assignPhotoToVacancySearch(photo.getFileName(), id);
    }
}
