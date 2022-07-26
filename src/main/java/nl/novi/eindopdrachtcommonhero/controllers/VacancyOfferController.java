package nl.novi.eindopdrachtcommonhero.controllers;

import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancyOfferData;
import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancyOfferRequest;
import nl.novi.eindopdrachtcommonhero.exceptions.BadRequestException;
import nl.novi.eindopdrachtcommonhero.exceptions.VacancyNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.VacancyOffer;
import nl.novi.eindopdrachtcommonhero.services.VacancyOfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/vacancies/offer")
public class VacancyOfferController {

    private final VacancyOfferService vacancyService;
    private final PhotoController photoController;

    public VacancyOfferController(VacancyOfferService vacancyService, PhotoController photoController) {
        this.vacancyService = vacancyService;
        this.photoController = photoController;
    }

    @GetMapping
    @Transactional
    public List<VacancyOffer> getVacancies(){
        List<VacancyOffer> vacancies;

        vacancies  = vacancyService.getOfferVacancies();

        return vacancies;
    }

    @PostMapping
    public ResponseEntity<Object> createOfferVacancy(@RequestBody VacancyOfferRequest vacancyOfferRequest){
        try{
        vacancyService.createOfferVacancy(vacancyOfferRequest);
        return new ResponseEntity<>("Vacature aangemaakt", HttpStatus.CREATED);
        } catch(BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public void getOfferVacancy(@PathVariable Long id){
        try {
            this.vacancyService.getOfferVacancy(id);
        } catch (VacancyNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public VacancyOfferData updateVacancyOffer(@RequestBody VacancyOfferRequest vacancyOfferRequest){
        try{
            return vacancyService.updateVacancyOffer(vacancyOfferRequest.id, vacancyOfferRequest);
        } catch(VacancyNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteVacancy(@PathVariable Long id){
        try{
            this.vacancyService.deleteOfferVacancy(id);
        } catch (VacancyNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/photo")
    public void assignPhotoToVacancyOffer(@PathVariable("id") Long id, @RequestBody MultipartFile file){
        FileUploadResponse photo = photoController.singleFileUpload(file);

        vacancyService.assignPhotoToVacancyOffer(photo.getFileName(), id);
    }
}