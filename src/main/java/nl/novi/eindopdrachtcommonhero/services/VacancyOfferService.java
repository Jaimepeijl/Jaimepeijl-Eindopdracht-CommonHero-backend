package nl.novi.eindopdrachtcommonhero.services;

import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancyOfferData;
import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancyOfferRequest;
import nl.novi.eindopdrachtcommonhero.exceptions.BadRequestException;
import nl.novi.eindopdrachtcommonhero.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtcommonhero.exceptions.VacancyNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.User;
import nl.novi.eindopdrachtcommonhero.models.VacancyOffer;
import nl.novi.eindopdrachtcommonhero.repositories.FileUploadRepository;
import nl.novi.eindopdrachtcommonhero.repositories.VacancyOfferRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VacancyOfferService {

    private final VacancyOfferRepository vacancyOfferRepository;
    private final FileUploadRepository uploadRepository;
    private final UserService userService;

    public VacancyOfferService(VacancyOfferRepository vacancyOfferRepository, FileUploadRepository uploadRepository, UserService userService) {
        this.vacancyOfferRepository = vacancyOfferRepository;
        this.uploadRepository = uploadRepository;
        this.userService = userService;
    }

    public List<VacancyOffer> getOfferVacancies(){
        return vacancyOfferRepository.findAll();
    }

    public VacancyOffer getOfferVacancy(Long id) {
        return this.vacancyOfferRepository.findById(id)
                .orElseThrow(VacancyNotFoundException::new);
    }

    public void deleteOfferVacancy(Long id) {
        if(!vacancyOfferRepository.existsById(id)){
            throw new VacancyNotFoundException();
        }
        vacancyOfferRepository.deleteById(id);
    }
    public VacancyOffer createOfferVacancy (VacancyOfferRequest vacancyOfferRequest){
        try{
            VacancyOffer vacancyOffer = toVacancyOffer(vacancyOfferRequest);
            return vacancyOfferRepository.save(vacancyOffer);
        } catch (Exception ex) {
            throw new BadRequestException("Kan vacature niet aanmaken");
        }
    }
    public VacancyOfferData updateVacancyOffer(Long id, VacancyOfferRequest newVacancy) {
        if (!vacancyOfferRepository.existsById(id)) throw new RecordNotFoundException();

        VacancyOffer vacancyOffer = this.getOfferVacancy(id);

        vacancyOffer.setTitle(newVacancy.title);
        vacancyOffer.setHours(newVacancy.hours);
        vacancyOffer.setDescription(newVacancy.description);
        vacancyOffer.setCity(newVacancy.city);

        this.vacancyOfferRepository.save(vacancyOffer);
        return this.createVacancyOfferData(vacancyOffer);
    }

    public VacancyOffer toVacancyOffer(VacancyOfferRequest vacancyOfferRequest){
        var vacancy = new VacancyOffer();

        vacancy.setId(vacancyOfferRequest.getId());
        vacancy.setPublisher(vacancyOfferRequest.getPublisher());
        vacancy.setTitle(vacancyOfferRequest.getTitle());
        vacancy.setHours(vacancyOfferRequest.getHours());
        vacancy.setDescription(vacancyOfferRequest.getDescription());
        vacancy.setCity(vacancyOfferRequest.getCity());

        return vacancy;
    }

    public VacancyOfferData createVacancyOfferData(VacancyOffer vacancyOffer) {
        return new VacancyOfferData(
                vacancyOffer.getPublisher(),
                vacancyOffer.getTitle(),
                vacancyOffer.getHours(),
                vacancyOffer.getDescription(),
                vacancyOffer.getCity()
        );
    }

    public void assignPhotoToVacancyOffer(String fileName, Long id) {
        Optional<VacancyOffer> optionalVacancy = vacancyOfferRepository.findById(id);
        Optional<FileUploadResponse> fileUploadResponse = uploadRepository.findByFileName(fileName);
        if (optionalVacancy.isPresent() && fileUploadResponse.isPresent()){
            FileUploadResponse photo = fileUploadResponse.get();
            VacancyOffer vacancyOffer = optionalVacancy.get();
            vacancyOffer.setFile(photo);
            vacancyOfferRepository.save(vacancyOffer);
        }
    }
}
