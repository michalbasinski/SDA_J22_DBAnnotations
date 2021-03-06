package pl.sda.rental.movies;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.rental.movies.dto.MovieDto;
import pl.sda.rental.movies.domain.MovieFacade;

@RestController
@AllArgsConstructor
public class MovieController {

    private MovieFacade movieFacade;

    @PostMapping("/movies")
    public ResponseEntity addPost(@RequestBody MovieDto movieDto) {
        MovieDto result = movieFacade.saveMovie(movieDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity getMovies(@PathVariable Long id) {
        MovieDto result = movieFacade.getMovie(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
