package ser531.group13.movierecommendation;

import java.util.List;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

public class FuskiTmdb {
	 static String movieName = "";
	static String URI_TMDB = "http://35.192.127.228:3030/tmdb/query";
	static String  queryTMDB="";
	
	public static void getResults(Movie movie) {
		
		System.out.println("movie name in tmdb:"+movieName);
		
		queryTMDB ="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
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
				"SELECT\r\n" + 
				"?title \r\n" + 
				"?genre \r\n" + 
				"?tmdbRating \r\n" + 
				"?voteCount  \r\n" + 
				"?cast  \r\n" + 
				"?director \r\n" + 
				"?overView \r\n" + 
				"?runtime \r\n" + 
				"?tagline \r\n" + 
				"?avg\r\n" + 
				"    WHERE { \r\n" + 
				"        ?UniqueID tmdb:hasVoteAvg ?tmdbRating;\r\n" + 
				"                    tmdb:hasVoteCount ?voteCount;\r\n" + 
				"                    tmdb:hasCast ?cast;\r\n" + 
				"                    tmdb:hasDirector ?director;\r\n" + 
				"                    tmdb:hasOverview ?overView;\r\n" + 
				"                    tmdb:hasRuntime ?runtime;\r\n" + 
				"                    tmdb:hasTagline ?tagline;\r\n" + 
				"                   tmdb:hasTmdbId ?tmdbid.\r\n" + 
				"\r\n" + 
				"        SERVICE <http://104.154.243.162:3030/movieLens/sparql>\r\n" + 
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
				"#  FILTER((xsd:integer(?insideTmdbID)) = (?tmdbid))\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"";
		
		
		
		loadClasses(URI_TMDB, queryTMDB,movie);
	}

	private static void loadClasses(String URI, String query,Movie movie) {
		QueryExecution q = QueryExecutionFactory.sparqlService(URI, query);
		ResultSet results = q.execSelect();
//		ResultSetFormatter.out(System.out,results);
		List<QuerySolution> res =ResultSetFormatter.toList(results);
		for(QuerySolution sol :res) {
			System.out.println(sol);

			movie.tmdbRating = sol.get("?tmdbRating").toString().split("\\^+")[0];
			movie.voteCount = sol.get("?voteCount").toString().split("\\^+")[0];
			movie.runtime = sol.get("?runtime").toString().split("\\^+")[0];
			movie.title =  sol.get("?title").toString();
			movie.director = sol.get("?director").toString();
			movie.overview = sol.get("?overView").toString();
			movie.tagline = sol.get("?tagline").toString();
			movie.avgUserRating = sol.get("?avg").toString().substring(0, 3);
			
			String[] cast = sol.get("?cast").toString().split("\\|");
			
			for(int i=0; i< cast.length; i++) {
				System.out.println(cast[i]);
				movie.cast.add(cast[i]);
			}
			

		}
		
	}
}
