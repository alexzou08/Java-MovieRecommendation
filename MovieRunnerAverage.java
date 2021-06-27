
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
 
    public void printAverageRatings() {
        //SecondRatings sr = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        SecondRatings sr = new SecondRatings();
        System.out.println("Number of movies in total: " + sr.getMovieSize());
        System.out.println("Number of raters in total: " + sr.getRaterSize());
        ArrayList<Rating> avgRatings = sr.getAverageRatings(12);
        Collections.sort(avgRatings);
        System.out.println("Total movies rated in average is " 
                            + avgRatings.size());
        for(Rating r : avgRatings) {
            String movieTitle = sr.getTitle(r.getItem());
            Double avgRating = r.getValue();
            System.out.println(avgRating + "\t" + movieTitle);
            break; // print out the first one and break 
        }
        
    }
    
    public void getAverageRatingOneMovie() {
        //SecondRatings sr = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        SecondRatings sr = new SecondRatings();
        //String movieTitle = "The Maze Runner";
        //String movieTitle = "Moneyball";
        String movieTitle = "Vacation";
        String id = sr.getID(movieTitle);
        Double avgRating = sr.getAverageByID(id, 1);
        System.out.println(movieTitle + "\t" + avgRating);
    }
}
