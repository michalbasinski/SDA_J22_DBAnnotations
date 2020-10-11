package pl.sda.rental.movies.domain;

import lombok.AllArgsConstructor;
import pl.sda.rental.movies.dto.MovieDto;
import pl.sda.rental.movies.dto.PersonDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class MovieFacade {

    private MovieRepository movieRepository;
    private PersonRepository personRepository;

    private MovieBuilderService movieBuilderService;

    public MovieDto saveMovie(MovieDto movie) {
        Movie movieEntity = movieBuilderService.entityFromDto(movie);

        List<PersonDto> directors = movie.getDirectors();
        List<Person> directorsFromDB = new ArrayList<>();
        directors.stream().filter(x -> (x.getId() != null && !x.getId().isEmpty()))
                .forEach(x -> directorsFromDB.add(personRepository.findById(Long.parseLong(x.getId())).get()));

        movieEntity.setDirectors(directorsFromDB);

        Movie savedMovie = movieRepository.save(movieEntity);

        return movieBuilderService.dtoFromEntity(savedMovie);
    }

    public MovieDto getMovie(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movieBuilderService.dtoFromEntity(movie.get());
    }
}
