
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    
    public double getAverageByID(String id, int minimalRaters) {
        double count = 0.0;
        double sumRatings = 0.0;
        for(Rater r : RaterDatabase.getRaters()) {
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
    
    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0;
        ArrayList<String> myRatedMovies = me.getItemsRated();
        for(String id : myRatedMovies) {
            if(r.hasRating(id)) {
                double myRating = me.getRating(id);
                double rRating = r.getRating(id);
                myRating -= 5;
                rRating -= 5;
                dotProduct += myRating * rRating;
            }
        }
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()) {
            if(!r.equals(me)) {
                double dotProduct = dotProduct(me, r);
                if(dotProduct > 0) {
                    list.add(new Rating(r.getID(), dotProduct));
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, 
                                               int minimalRaters) {
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarities(id);
        //add filter to the movies
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for (String movieID : movies) {
            double weightedAverage = 0;
            double sum = 0;
            int countRaters = 0;
            for(int i = 0; i < numSimilarRaters; i++) {
                Rating r = list.get(i);
                double weight = r.getValue();
                String raterID = r.getItem();
                Rater myRater = RaterDatabase.getRater(raterID);
                if(myRater.hasRating(movieID)) {
                    countRaters ++;
                    sum += weight * myRater.getRating(movieID);
                }
            }
            
            if(countRaters >= minimalRaters) {
                weightedAverage = sum / countRaters;
                ret.add(new Rating(movieID, weightedAverage));
            }
        }
        
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, 
                                              int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarities(id);
        //add filter to the movies
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        for (String movieID : movies) {
            double weightedAverage = 0;
            double sum = 0;
            int countRaters = 0;
            for(int i = 0; i < numSimilarRaters; i++) {
                Rating r = list.get(i);
                double weight = r.getValue();
                String raterID = r.getItem();
                Rater myRater = RaterDatabase.getRater(raterID);
                if(myRater.hasRating(movieID)) {
                    countRaters ++;
                    sum += weight * myRater.getRating(movieID);
                }
            }
            
            if(countRaters >= minimalRaters) {
                weightedAverage = sum / countRaters;
                ret.add(new Rating(movieID, weightedAverage));
            }
        }
        
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
}
