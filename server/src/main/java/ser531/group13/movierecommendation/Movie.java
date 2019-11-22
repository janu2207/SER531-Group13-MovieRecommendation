package ser531.group13.movierecommendation;
import java.util.*;
import lombok.*;

public class Movie {

	String avgUserRating;
	String title;
	String tmdbRating;
	List<String> genre;
	String voteCount;
	List<String> cast;
	String overview;
	String runtime;
	String tagline;
	String imdbRating;
	String ratingCount;
	String duration;
	String year;
	String numberOfNominations;
	String numberOfUserReviews;
	String director;
	List<String> moviesRecommended;
	
	public String getAvgUserRating() {
		return avgUserRating;
	}
	public void setAvgUserRating(String avgUserRating) {
		this.avgUserRating = avgUserRating;
	}
	public String getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(String ratingCount) {
		this.ratingCount = ratingCount;
	}
	
	public List<String> getMoviesRecommended() {
		return moviesRecommended;
	}
	public void setMoviesRecommended(List<String> moviesRecommended) {
		this.moviesRecommended = moviesRecommended;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTmdbRating() {
		return tmdbRating;
	}
	public void setTmdbRating(String tmdbRating) {
		this.tmdbRating = tmdbRating;
	}
	public List<String> getGenre() {
		return genre;
	}
	public void setGenre(List<String> genre) {
		this.genre = genre;
	}
	public String getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(String voteCount) {
		this.voteCount = voteCount;
	}
	public List<String> getCast() {
		return cast;
	}
	public void setCast(List<String> cast) {
		this.cast = cast;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getNumberOfNominations() {
		return numberOfNominations;
	}
	public void setNumberOfNominations(String numberOfNominations) {
		this.numberOfNominations = numberOfNominations;
	}
	public String getNumberOfUserReviews() {
		return numberOfUserReviews;
	}
	public void setNumberOfUserReviews(String numberOfUserReviews) {
		this.numberOfUserReviews = numberOfUserReviews;
	}
	
	
	public Movie() {
		this.genre = new ArrayList<>();
		this.moviesRecommended = new ArrayList<>();
		this.cast = new ArrayList<>();
		
	}
	public Movie(String title, String tmdbRating, List<String> genre, String voteCount, List<String> cast, String director,
			String overview, String runtime, String tagline, String imdbRating, String duration, String year,
			String numberOfNominations, String numberOfUserReviews, List<String> moviesRecommended) {
		
		this.title = title;
		this.tmdbRating = tmdbRating;
		this.genre = genre;
		this.voteCount = voteCount;
		this.cast = cast;
		this.director = director;
		this.overview = overview;
		this.runtime = runtime;
		this.tagline = tagline;
		this.imdbRating = imdbRating;
		this.duration = duration;
		this.year = year;
		this.numberOfNominations = numberOfNominations;
		this.numberOfUserReviews = numberOfUserReviews;
		this.moviesRecommended = moviesRecommended;
	}
	
}
	

