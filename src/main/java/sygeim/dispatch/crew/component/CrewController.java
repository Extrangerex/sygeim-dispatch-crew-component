package sygeim.dispatch.crew.component;

import io.micronaut.http.annotation.*;
import sygeim.dispatch.crew.component.commands.CreateCrewCommand;
import sygeim.dispatch.crew.component.commands.UpdateCrewCommand;
import sygeim.dispatch.crew.component.domain.Crew;
import sygeim.dispatch.crew.component.repository.CrewRepositoryImpl;

import java.util.List;
import java.util.Optional;

@Controller("/crew")
public class CrewController {

    private final CrewRepositoryImpl crewRepository;

    public CrewController(CrewRepositoryImpl crewRepository) {
        this.crewRepository = crewRepository;
    }

    @Get("/{id}")
    Optional<Crew> findById(@PathVariable("id") String id) {
        return crewRepository.findById(id);
    }

    @Get("/nationality/{nationality}")
    Optional<List<Crew>> findByNationality(@Body String nationality) {
        return crewRepository.findByNationality(nationality);
    }

    @Put("/{id}")
    int update(@PathVariable("id") String id, @Body UpdateCrewCommand updateCrewCommand) {
        return crewRepository.update(id, updateCrewCommand);
    }

    @Delete("/{id}")
    int delete(@PathVariable("id") String id) {
        return crewRepository.delete(id);
    }

    @Post("/")
    Optional<Crew> create(@Body CreateCrewCommand createCrewCommand) {
        return crewRepository.create(createCrewCommand);
    }
}