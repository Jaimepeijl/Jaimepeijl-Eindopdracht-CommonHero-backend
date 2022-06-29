package nl.novi.eindopdrachtcommonhero.controllers;

import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancyData;
import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancyRequest;
import nl.novi.eindopdrachtcommonhero.exceptions.VacancyNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.Vacancy;
import nl.novi.eindopdrachtcommonhero.services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/vacancies")
public class VacancyController {

    private final VacancyService vacancyService;
    private final PhotoController photoController;

    public VacancyController(VacancyService vacancyService, PhotoController photoController) {
        this.vacancyService = vacancyService;
        this.photoController = photoController;
    }

    @GetMapping
    @Transactional
    public List<Vacancy> getVacancies(){
        List<Vacancy> vacancies;

        vacancies  = vacancyService.getVacancies();

        return vacancies;
    }

    @PostMapping
    private ResponseEntity<Object> createVacancy(@RequestBody VacancyRequest vacancyRequest){

        VacancyData vacancyData = vacancyService.createVacancy(vacancyRequest);

        return ResponseEntity.created(null).body(vacancyData);

    }

    @GetMapping("/vacancies/{id}")
    private void getVacancy(@PathVariable Long id){
        try {
            this.vacancyService.getVacancy(id);
        } catch (VacancyNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    private VacancyData updateVacancy(@RequestBody VacancyRequest vacancyRequest){
        try{
            return vacancyService.updateVacancy(vacancyRequest.id, vacancyRequest);
        } catch(VacancyNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/vacancies/{id}")
    private void deleteVacancy(@PathVariable Long id){
        try{
            this.vacancyService.deleteVacancy(id);
        } catch (VacancyNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/vacancies/{id}/photo")
    public void assignPhotoToVacancy(@PathVariable("id") Long id, @RequestBody MultipartFile file){
        FileUploadResponse photo = photoController.singleFileUpload(file);

        vacancyService.assignPhotoToVacancy(photo.getFileName(), id);
    }
}
