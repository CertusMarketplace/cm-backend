package pe.edu.certus.peoplemodule.logic.adapters.driven;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.driver.ForPeople;
import pe.edu.certus.peoplemodule.repository.entity.PeopleEntity;
import pe.edu.certus.peoplemodule.repository.ports.driver.ForQueryingPeople;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeopleManager implements ForPeople {

    private final ForQueryingPeople forQueryingPeople;

    @Override
    public PeopleModel createPeople(PeopleModel peopleModel) {
        PeopleEntity peopleEntity = PeopleEntity.builder()
                .idUser(peopleModel.getIdUser())
                .personName(peopleModel.getPersonName())
                .personLastname(peopleModel.getPersonLastname())
                .personInstitutionalEmail(peopleModel.getPersonInstitutionalEmail())
                .build();

        PeopleEntity savedEntity = forQueryingPeople.save(peopleEntity);
        return mapEntityToModel(savedEntity);
    }

    @Override
    public PeopleModel updatePeople(PeopleModel peopleModel) {
        PeopleEntity peopleEntity = forQueryingPeople.findById(peopleModel.getPersonId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID: " + peopleModel.getPersonId()));

        peopleEntity.setPersonName(peopleModel.getPersonName());
        peopleEntity.setPersonLastname(peopleModel.getPersonLastname());
        peopleEntity.setPersonDni(peopleModel.getPersonDni());
        peopleEntity.setPersonMobilePhone(peopleModel.getPersonMobilePhone());
        peopleEntity.setPersonGender(peopleModel.getPersonGender());
        peopleEntity.setPersonInstituteCampus(peopleModel.getPersonInstituteCampus());
        peopleEntity.setPersonInstitutionalEmail(peopleModel.getPersonInstitutionalEmail());
        peopleEntity.setPersonInstitutionalCareer(peopleModel.getPersonInstitutionalCareer());
        peopleEntity.setPersonInstitutionalCycle(peopleModel.getPersonInstitutionalCycle());

        PeopleEntity updatedEntity = forQueryingPeople.save(peopleEntity);
        return mapEntityToModel(updatedEntity);
    }

    @Override
    public PeopleModel findPeopleById(Long personId) {
        PeopleEntity peopleEntity = forQueryingPeople.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID de persona: " + personId));
        return mapEntityToModel(peopleEntity);
    }

    @Override
    public PeopleModel findPeopleByIdUser(Long idUser) {
        PeopleEntity peopleEntity = forQueryingPeople.findByIdUser(idUser)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID de usuario: " + idUser));
        return mapEntityToModel(peopleEntity);
    }

    @Override
    public List<PeopleModel> findAllPeople() {
        return forQueryingPeople.findAll().stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePeople(Long id) {
        if (!forQueryingPeople.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar, persona no encontrada con ID: " + id);
        }
        forQueryingPeople.deleteById(id);
    }

    @Override
    public void requestSellerRole(PeopleModel peopleModel, Long userId) {
        PeopleModel existingPerson = this.findPeopleByIdUser(userId);

        existingPerson.setPersonDni(peopleModel.getPersonDni());
        existingPerson.setPersonMobilePhone(peopleModel.getPersonMobilePhone());
        existingPerson.setPersonInstituteCampus(peopleModel.getPersonInstituteCampus());
        existingPerson.setPersonInstitutionalEmail(peopleModel.getPersonInstitutionalEmail());
        existingPerson.setPersonInstitutionalCareer(peopleModel.getPersonInstitutionalCareer());
        existingPerson.setPersonInstitutionalCycle(peopleModel.getPersonInstitutionalCycle());

        this.updatePeople(existingPerson);
    }

    private PeopleModel mapEntityToModel(PeopleEntity entity) {
        return PeopleModel.builder()
                .personId(entity.getPersonId())
                .idUser(entity.getIdUser())
                .personName(entity.getPersonName())
                .personLastname(entity.getPersonLastname())
                .personDni(entity.getPersonDni())
                .personMobilePhone(entity.getPersonMobilePhone())
                .personGender(entity.getPersonGender())
                .personInstituteCampus(entity.getPersonInstituteCampus())
                .personInstitutionalEmail(entity.getPersonInstitutionalEmail())
                .personInstitutionalCareer(entity.getPersonInstitutionalCareer())
                .personInstitutionalCycle(entity.getPersonInstitutionalCycle())
                .build();
    }
}