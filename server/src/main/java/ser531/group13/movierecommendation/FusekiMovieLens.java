package ser531.group13.movierecommendation;

import java.util.List;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

public class FusekiMovieLens {
	static String URI_MovieLens = "http://104.154.243.162:3030/movieLens/query";
	static Double avgRating = 0.0;
	static String genre="";
	
	static String queryMovieLens="";
	
	public static void getResults(Movie movie) {
		
		queryMovieLens ="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
				"PREFIX imdb: <http://www.semanticweb.org/ontologies/Imdb-Ontology#>\r\n" + 
				"PREFIX tmdb: <http://www.semanticweb.org/hp/ontologies/2019/10/tmdb-ontology#>\r\n" + 
				"PREFIX movielens: <http://www.semanticweb.org/hp/ontologies/2019/10/movielens-onology#>\r\n" + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
				"PREFIX im: <http://imgpedia.dcc.uchile.cl/resource/>\r\n" + 
				"\r\n" + 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
				"PREFIX imdb: <http://www.semanticweb.org/ontologies/Imdb-Ontology#>\r\n" + 
				"PREFIX tmdb: <http://www.semanticweb.org/hp/ontologies/2019/10/tmdb-ontology#>\r\n" + 
				"PREFIX movielens: <http://www.semanticweb.org/hp/ontologies/2019/10/movielens-onology#>\r\n" + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"SELECT ?title \r\n" + 
				"WHERE {\r\n" + 
				"       {\r\n" + 
				"        SELECT ?title (AVG(?rating) AS ?avg) ?genre\r\n" + 
				"        WHERE { \r\n" + 
				"          ?uniqueID movielens:hasTitle ?title;\r\n" + 
				"                    movielens:hasRatingValue ?rating;\r\n" + 
				"                    movielens:hasGenre ?genre;               \r\n" + 
				"        }\r\n" + 
				"        GROUP BY ?title ?genre\r\n" + 
				"  }\r\n" + 
				"  FILTER (?avg > "+avgRating+"-0.5 && ?avg < "+avgRating+"+0.5).\r\n" + 
				"  FILTER regex(str(?genre),\""+genre+"\").\r\n" + 
				"}\r\n" + 
				"ORDER BY DESC(?avg)\r\n" + 
				"LIMIT 10";
		
		loadClasses(URI_MovieLens, queryMovieLens,movie);
	}

	private static void loadClasses(String URI, String query,Movie movie) {
		QueryExecution q = QueryExecutionFactory.sparqlService(URI, query);
		ResultSet results = q.execSelect();
//		ResultSetFormatter.out(System.out,results);
		List<QuerySolution> res =ResultSetFormatter.toList(results);
		for(QuerySolution sol :res) {
			System.out.println(sol);

			movie.moviesRecommended.add(sol.get("?title").toString());
//			System.out.println(sol.get("?tmdbRating").toString().split("\\^+")[0]);
//			System.out.println(sol.get("?voteCount").toString().split("\\^+")[0]);
//			System.out.println(sol.get("?runtime").toString().split("\\^+")[0]);

		}
		
	}
}
