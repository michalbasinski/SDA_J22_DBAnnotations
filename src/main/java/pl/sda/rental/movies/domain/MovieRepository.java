package pl.sda.rental.movies.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface MovieRepository extends JpaRepository<Movie, Long> {
}
