package nl.novi.eindopdrachtcommonhero.services;

import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancyOfferData;
import nl.novi.eindopdrachtcommonhero.controllers.dto.VacancyOfferRequest;
import nl.novi.eindopdrachtcommonhero.exceptions.BadRequestException;
import nl.novi.eindopdrachtcommonhero.exceptions.VacancyNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.VacancyOffer;
import nl.novi.eindopdrachtcommonhero.repositories.FileUploadRepository;
import nl.novi.eindopdrachtcommonhero.repositories.VacancyOfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyOfferService {

    private final VacancyOfferRepository vacancyOfferRepository;
    private final FileUploadRepository uploadRepository;

    public VacancyOfferService(VacancyOfferRepository vacancyOfferRepository, FileUploadRepository uploadRepository) {
        this.vacancyOfferRepository = vacancyOfferRepository;
        this.uploadRepository = uploadRepository;
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
        if (!vacancyOfferRepository.existsById(id)) throw new VacancyNotFoundException();

        VacancyOffer vacancyOffer = this.getOfferVacancy(id);

        vacancyOffer.setTitle(newVacancy.title);
        vacancyOffer.setHours(newVacancy.hours);
        vacancyOffer.setDescription(newVacancy.description);
        vacancyOffer.setCity(newVacancy.city);
        vacancyOffer.setRepeats(newVacancy.repeats);
        vacancyOffer.setDate(newVacancy.date);

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
        vacancy.setRepeats(vacancyOfferRequest.getRepeats());
        vacancy.setDate(vacancyOfferRequest.getDate());

        return vacancy;
    }

    public VacancyOfferData createVacancyOfferData(VacancyOffer vacancyOffer) {
        return new VacancyOfferData(
                vacancyOffer.getPublisher(),
                vacancyOffer.getTitle(),
                vacancyOffer.getHours(),
                vacancyOffer.getDescription(),
                vacancyOffer.getCity(),
                vacancyOffer.getRepeats(),
                vacancyOffer.getDate()
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
