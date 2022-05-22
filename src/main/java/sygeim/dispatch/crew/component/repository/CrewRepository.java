package sygeim.dispatch.crew.component.repository;

import sygeim.dispatch.crew.component.commands.CreateCrewCommand;
import sygeim.dispatch.crew.component.commands.UpdateCrewCommand;
import sygeim.dispatch.crew.component.domain.Crew;

import java.util.List;
import java.util.Optional;

public interface CrewRepository {
    Optional<Crew> findById(String id);
    Optional<List<Crew>> findByNationality(String nationality);
    Optional<Crew> create(CreateCrewCommand createCrewCommand);
    int delete(String id);
    int update(String id, UpdateCrewCommand crew);
}
