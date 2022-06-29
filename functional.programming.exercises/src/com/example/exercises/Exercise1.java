package com.example.exercises;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import com.example.domain.Director;
import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise1 {
	private static final MovieService movieService = InMemoryMovieService.getInstance();

	public static void main(String[] args) {
		// Find the number of movies of each director
        final Collection<Movie> movies = movieService.findAllMovies();
        Function<Director,Director> byDirector = d -> d;
		var numOfMoviesOfEachDirector =
        movies.stream()                            // Stream<Movie>
       
              .map(Movie::getDirectors)            // Stream<List<Director>>
              .flatMap(List::stream)               // Stream<Director>
              .collect(groupingBy(byDirector,counting())); 
        numOfMoviesOfEachDirector.forEach((director, count)-> System.out.println("%s: %d".formatted(director.getName(),count)));
	}

}
