
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        // have already assigned the data folder to "data/" in loadMovies and loadRaters
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        double count = 0.0;
        double sumRatings = 0.0;
        for(EfficientRater r : myRaters) {
            if(r.hasRating(id)) {
                sumRatings += r.getRating(id);
                count ++;
            }
        }
        
        //return(count >= minimalRaters) ? ((double)(sumRatings)/count) : 0.0;
        //or you can write it the following way
        if(count >= minimalRaters) {
            return (double)sumRatings/count;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        for(Movie m : myMovies) {
            String movieID = m.getID();
            double avgRating = getAverageByID(movieID, minimalRaters);
            Rating averageRating = new Rating(movieID, avgRating);
            if(avgRating != 0.0) {
                averageRatings.add(averageRating);
            }
        }
        return averageRatings;
    }
    
    public String getTitle(String id) {
        for(Movie m : myMovies) {
            if(m.getID().equals(id)) {
                return m.getTitle();
            }
        }
        return "Movie " + id + " not found";
    }
    
    //the movie title must be spelled exactly as it appears in the movie data files.
    public String getID(String movieTitle) {
        for(Movie m : myMovies) {
            if(m.getTitle().equals(movieTitle)) {
                return m.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
}
