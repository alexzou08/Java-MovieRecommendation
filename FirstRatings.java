
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord rec : parser) {
            Movie movie = new Movie(
                            rec.get("id"),
                            rec.get("title"),
                            rec.get("year"),
                            rec.get("genre"),
                            rec.get("director"),
                            rec.get("country"),
                            rec.get("poster"),
                            Integer.parseInt(rec.get("minutes")));
            movieList.add(movie);                
        }
        return movieList;
    }
    
    public void testLoadMovies() {
        int comedyCount = 0;
        int longMoviesCount = 0;
        int moviesPerDirector = 0;
        HashMap<String, Integer> directorMovies = new HashMap<String, Integer>();
        
        //ArrayList<Movie> movieList = loadMovies("ratedmovies_short.csv");
        ArrayList<Movie> movieList = loadMovies("ratedmoviesfull.csv");
        System.out.println("# movies: " + movieList.size());
        
        for(Movie m : movieList) {
            //System.out.println(m);
            if(m.getGenres().contains("Comedy")) {
                comedyCount++;
            }
            if(m.getMinutes() > 150) {
                longMoviesCount++;
            }

            String director = m.getDirector();
            if(! directorMovies.containsKey(director)) {
                directorMovies.put(director, 1);
            } else {
                int n = directorMovies.get(director);
                directorMovies.put(director, n+1);
            }
        }
        System.out.println("Number of comedies: " + comedyCount);
        System.out.println("Number of movies longer than 150 minutes: " + longMoviesCount);
        int maxMovies = 0;
        int maxMoviesDirector = 0;
        for(String director : directorMovies.keySet()) {
            if(directorMovies.get(director) > maxMovies) {
                maxMovies = directorMovies.get(director);
            }
        }
        System.out.println("Number of maximum movies by one derector: " + maxMovies);
        for(String director : directorMovies.keySet()) {
            if(directorMovies.get(director) == maxMovies) {
                maxMoviesDirector++;
            }
        }
        System.out.println("There are " + maxMoviesDirector + " directors in those movies, and they are: ");
        for(String director : directorMovies.keySet()) {
            if(directorMovies.get(director) == maxMovies) {
                System.out.println(director);
            }
        }
    }
    
    public ArrayList<EfficientRater> loadRaters(String filename) {
        ArrayList<EfficientRater> raterList = new ArrayList<EfficientRater>();
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord rec : parser) {
            String raterID = rec.get("rater_id");
            boolean found = false;
            for(int i = 0; i < raterList.size(); i++) { 
                if(raterList.get(i).getID().equals(raterID)) {
                    EfficientRater r = raterList.get(i);
                    r.addRating(rec.get("movie_id"),Double.parseDouble(rec.get("rating")));
                    raterList.set(i,r);
                    found = true;
                    break;
                }
            }
            
            if(!found) {
                EfficientRater r = new EfficientRater(raterID);
                r.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
                raterList.add(r);
            }
        }
        return raterList;
    }
    
    public void testLoadRaters() {
        //ArrayList<EfficientRater> raterList = loadRaters("ratings_short.csv");
        ArrayList<EfficientRater> raterList = loadRaters("ratings.csv");
        System.out.println("Number of raters: " + raterList.size());
        /*
        for(Rater r : raterList) {
            System.out.println("Rater: " + r.getID() + ", has " + 
                               r.getItemsRated().size() + " ratings:");
            ArrayList<String> itemsRated = r.getItemsRated();
            for(int i = 0; i < itemsRated.size(); i++) {
                System.out.println(itemsRated);
            }
        }
        */
        String raterID = "193";
        for(EfficientRater r : raterList) {
            if(r.getID().equals(raterID)) {
                System.out.println("Rater " + raterID + ", has " + 
                                  r.getItemsRated().size() + " ratings." );
            }
        }
        
        int numOfMaxRatings = 0;
        for(EfficientRater r : raterList) {
            if(r.getItemsRated().size() > numOfMaxRatings) {
                numOfMaxRatings = r.getItemsRated().size();
            }
        }
        System.out.println("Number of maximum ratings per rater: " + numOfMaxRatings);
        System.out.println("Raters with " + numOfMaxRatings + " ratings are: ");
        int ratersWithMaxRatings = 0;
        for(EfficientRater r : raterList) {
            if(r.getItemsRated().size() == numOfMaxRatings) {
                ratersWithMaxRatings++;
                System.out.println(r.getID());
            }
        }
        System.out.println("There are " + ratersWithMaxRatings + 
                            " raters with " + numOfMaxRatings + " ratings.");
                            
        String movieID = "1798709";
        int numOfRatings = 0;
        for(EfficientRater r : raterList) {
            if(r.getItemsRated().contains(movieID)) {
                numOfRatings++;
            }
        }
        System.out.println("Number of ratings for " + movieID + " is: " + numOfRatings);
        
        ArrayList<String> numOfMovies = new ArrayList<String>();
        for(EfficientRater r : raterList) {
            for(int i = 0; i < r.getItemsRated().size(); i++) {
                String rated = r.getItemsRated().get(i);
                if(!numOfMovies.contains(rated)) {
                    numOfMovies.add(rated);
                }
            }
        }
        System.out.println("Number of unique Movies rated by all raters: " +
                            numOfMovies.size());
    }
}
