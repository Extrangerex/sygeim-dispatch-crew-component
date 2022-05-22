package sygeim.dispatch.crew.component.repository;


import io.micronaut.transaction.annotation.ReadOnly;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import jakarta.inject.Singleton;
import sygeim.dispatch.crew.component.commands.CreateCrewCommand;
import sygeim.dispatch.crew.component.commands.UpdateCrewCommand;
import sygeim.dispatch.crew.component.domain.Crew;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Singleton
public class CrewRepositoryImpl implements CrewRepository {

    private final EntityManager entityManager;

    public CrewRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @ReadOnly
    public Optional<Crew> findById(String id) {
        return Optional.ofNullable(entityManager.find(Crew.class, id));
    }

    @Override
    @ReadOnly
    public Optional<List<Crew>> findByNationality(String nationality) {
        return Optional.ofNullable(entityManager.createQuery("SELECT c from Crew c where c.nationality = :nationality", Crew.class).setParameter("nationality", nationality).getResultList());
    }

    @Override
    @TransactionalAdvice
    public Optional<Crew> create(CreateCrewCommand createCrewCommand) {
        Crew crew = new Crew();
        crew.setId(createCrewCommand.getId());
        crew.setName(createCrewCommand.getName());
        crew.setNationality(createCrewCommand.getNationality());
        crew.setPosition(createCrewCommand.getPosition());

        entityManager.persist(crew);

        return Optional.of(crew);
    }

    @Override
    @TransactionalAdvice
    public int delete(String id) {
        Optional<Crew> crew = findById(id);

        if (crew.isPresent()) {
            Crew crew1 = crew.get();
            crew1.setDeleted(true);

            entityManager.persist(crew1);
        }


        return 1;
    }

    @Override
    @TransactionalAdvice
    public int update(String id, UpdateCrewCommand updateCrewCommand) {
        Optional<Crew> crew = findById(id);

        if (crew.isPresent()) {
            Crew crew1 = crew.get();
            crew1.setName(updateCrewCommand.getName() == null ? updateCrewCommand.getName() : crew1.getName());
            crew1.setName(updateCrewCommand.getNationality() == null ? updateCrewCommand.getNationality() : crew1.getNationality());
            crew1.setName(updateCrewCommand.getPosition() == null ? updateCrewCommand.getPosition() : crew1.getPosition());

            entityManager.persist(crew1);
        }

        return 1;
    }
}
