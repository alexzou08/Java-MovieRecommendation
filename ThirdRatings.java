
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        // have already assigned the data folder to "data/" in loadMovies and loadRaters
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        for(String movieID : movies) {
            double avgRating = getAverageByID(movieID, minimalRaters);
            Rating averageRating = new Rating(movieID, avgRating);
            if(avgRating != 0.0) {
                averageRatings.add(averageRating);
            }
        }
        return averageRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        for(String id : movies) {
            double averageRating = getAverageByID(id, minimalRaters);
            if(averageRating != 0) {
                Rating r = new Rating(id, averageRating);
                averageRatings.add(r);
            }
        }
        return averageRatings;
    }
    
}
