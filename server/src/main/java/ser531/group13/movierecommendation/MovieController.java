package ser531.group13.movierecommendation;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
class MovieController {
    

	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/movies/{movieName}")
    @ResponseBody
    public String coolCars(@PathVariable String movieName) throws JsonProcessingException {
    	
    	
    	List<String> li = new ArrayList<>();
    	li.add("sdcs");
    	li.add("sdsds");
    	
    	
    	
    	System.out.println("movieName:"+movieName);
    	
    	FuskiTmdb.movieName = movieName.trim();
    	FusekiImdb.movieName = movieName.trim();
    	
    	Movie movie = new Movie();
    	FuskiTmdb.getResults(movie);
    	FusekiImdb.getResults(movie);
    	
    	Double imdbRating = Double.parseDouble(movie.imdbRating)/2;
    	Double tmdbRating = Double.parseDouble(movie.tmdbRating)/2;
    	Double avgUsrRating = Double.parseDouble(movie.avgUserRating);
    	
    	FusekiMovieLens.avgRating = (imdbRating + tmdbRating + avgUsrRating)/3;
    	FusekiMovieLens.genre = movie.genre.get(0);
    	FusekiMovieLens.getResults(movie);
    	
    	System.out.println("after getting results");
    	
        
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(movie);
    }
}


