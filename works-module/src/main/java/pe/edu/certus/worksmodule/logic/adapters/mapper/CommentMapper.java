package pe.edu.certus.worksmodule.logic.adapters.mapper;

import jakarta.persistence.EntityNotFoundException;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.driver.ForPeople;
import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.CommentWebModel;
import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.CreateCommentWebModel;
import pe.edu.certus.worksmodule.logic.model.CommentModel;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingComment;

import java.util.Collections;
import java.util.stream.Collectors;

public class CommentMapper implements ForMappingComment {

    // INYECTAMOS EL SERVICIO DE PEOPLE
    private final ForPeople<PeopleModel, Long> forPeople;

    public CommentMapper(ForPeople<PeopleModel, Long> forPeople) {
        this.forPeople = forPeople;
    }

    @Override
    public CommentModel fromRequest(CreateCommentWebModel request, Long userId) {
        return CommentModel.builder()
                .workId(request.workId())
                .userId(userId)
                .parentCommentId(request.parentCommentId())
                .commentBody(request.commentBody())
                .build();
    }

    @Override
    public CommentWebModel toWeb(CommentModel model) {
        if (model == null) return null;

        // VALORES POR DEFECTO
        String userName = "Usuario Anónimo";
        String userAvatarUrl = "/img/works/perfil-generico.png";

        // INTENTAMOS OBTENER EL PERFIL
        try {
            PeopleModel person = forPeople.findPeopleById(model.getUserId());
            if (person != null) {
                userName = person.getPersonName() + " " + person.getPersonLastname();
                if (person.getPersonProfileImageUrl() != null && !person.getPersonProfileImageUrl().isEmpty()) {
                    userAvatarUrl = person.getPersonProfileImageUrl();
                }
            }
        } catch (EntityNotFoundException e) {
                // Si el perfil no se encuentra, usamos los valores por defecto. No hacemos nada.
            System.err.println("No se encontró perfil para el usuario con ID: " + model.getUserId());
        }

        return new CommentWebModel(
                model.getId(),
                model.getUserId(),
                userName, // Usamos el nombre obtenido
                userAvatarUrl, // Usamos el avatar obtenido
                model.getCommentBody(),
                model.getCreatedAt(),
                model.getReplies() != null ? model.getReplies().stream().map(this::toWeb).collect(Collectors.toList()) : Collections.emptyList()
        );
    }
}