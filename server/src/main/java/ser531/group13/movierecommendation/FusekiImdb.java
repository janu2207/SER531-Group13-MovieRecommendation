package ser531.group13.movierecommendation;

import java.util.List;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

public class FusekiImdb {
	
	static String movieName = "";
	static String URI_IMDB = "http://35.226.30.169:3030/imdb/query";
	static String queryImdb="";
	
	public static void getResults(Movie movie) {
		
		queryImdb = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
				"PREFIX imdb: <http://www.semanticweb.org/ontologies/Imdb-Ontology#>\r\n" + 
				"PREFIX tmdb: <http://www.semanticweb.org/hp/ontologies/2019/10/tmdb-ontology#>\r\n" + 
				"PREFIX movielens: <http://www.semanticweb.org/hp/ontologies/2019/10/movielens-onology#>\r\n" + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#int>\r\n" + 
				"PREFIX im: <http://imgpedia.dcc.uchile.cl/resource/>\r\n" + 
				"\r\n" + 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
				"PREFIX imdb: <http://www.semanticweb.org/ontologies/Imdb-Ontology#>\r\n" + 
				"PREFIX tmdb: <http://www.semanticweb.org/hp/ontologies/2019/10/tmdb-ontology#>\r\n" + 
				"PREFIX movielens: <http://www.semanticweb.org/hp/ontologies/2019/10/movielens-onology#>\r\n" + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#int>\r\n"+
				"\r\n" + 
				"SELECT\r\n" + 
				"?title ?genre ?ImdbRating ?RatingCount  ?Duration  ?Year ?NumberOfNominations ?NumberOfUserReviews ?wordsInTitle ?avg\r\n" + 
				"    WHERE { \r\n" + 
				"        ?UniqueID imdb:hasImdbRating ?ImdbRating;\r\n" + 
				"                    imdb:hasRatingCount ?RatingCount;\r\n" + 
				"                    imdb:hasDuration ?Duration;\r\n" + 
				"                    imdb:hasYear ?Year;\r\n" + 
				"                    imdb:hasNumberOfNominations ?NumberOfNominations;\r\n" + 
				"                    imdb:hasNumberOfUserReviews ?NumberOfUserReviews;\r\n" + 
				"                    imdb:hasWordsInTitle ?wordsInTitle;\r\n" + 
				"                    imdb:hasImdbId ?imdbid.\r\n" + 
				"\r\n" + 
				"        SERVICE <http://104.154.243.162:3030/movieLens/query>\r\n" + 
				"        {\r\n" + 
				"            SELECT ?title (AVG(?rating) AS ?avg) ?genre ?tmdbid ?imdbid\r\n" + 
				"                WHERE { \r\n" + 
				"                  ?uniqueID movielens:hasTitle ?title;\r\n" + 
				"                            movielens:hasRatingValue ?rating;\r\n" + 
				"                            movielens:hasGenre ?genre;\r\n" + 
				"                            movielens:hasTmdbIdML ?tmdbid;\r\n" + 
				"                            movielens:hasImdbIdML ?imdbid;\r\n" + 
				"                }\r\n" + 
				"                GROUP BY ?title ?genre ?tmdbid ?imdbid\r\n" + 
				"                HAVING ((?title)=\""+movieName+"\")\r\n" + 
				"        }\r\n" + 
				"#		FILTER((?insideImdbID)=(?imdbid))\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"";
		
		loadClasses(URI_IMDB, queryImdb,movie);
	}

	private static void loadClasses(String URI, String query,Movie movie) {
		QueryExecution q = QueryExecutionFactory.sparqlService(URI, query);
		ResultSet results = q.execSelect();
//		ResultSetFormatter.out(System.out,results);
		List<QuerySolution> res =ResultSetFormatter.toList(results);
		for(QuerySolution sol :res) {
			System.out.println(sol);
			
			
			movie.imdbRating = sol.get("?ImdbRating").toString().split("\\^+")[0];
			movie.year = sol.get("?Year").toString();
			movie.ratingCount = sol.get("?RatingCount").toString().split("\\^+")[0];
			movie.duration =  sol.get("?Duration").toString().split("\\^+")[0];
			movie.numberOfNominations = sol.get("?NumberOfNominations").toString().split("\\^+")[0];
			movie.numberOfUserReviews = sol.get("?NumberOfUserReviews").toString().split("\\^+")[0];
				System.out.println(sol.get("?genre").toString());
			String[] genre = sol.get("?genre").toString().split("\\|");
			
			for(int i=0; i< genre.length; i++) {
				System.out.println(genre[i]);
				movie.genre.add(genre[i]);
			}
			
//
//			System.out.println(sol.get("?tmdbRating").toString().split("\\^+")[0]);
//			System.out.println(sol.get("?voteCount").toString().split("\\^+")[0]);
//			System.out.println(sol.get("?runtime").toString().split("\\^+")[0]);

		}
		
	}
}
