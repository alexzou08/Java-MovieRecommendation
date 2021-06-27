
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    
    public void printAverageRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of movies in total: " + MovieDatabase.size());
        System.out.println("Number of raters in total: " + tr.getRaterSize());
        // below is the target avgRatings ArrayList
        ArrayList<Rating> avgRatings = tr.getAverageRatings(35);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies:");
        for(Rating r : avgRatings) {
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            Double avgRating = r.getValue();
            System.out.println(avgRating + "\t" + movieTitle);
            break; // print out the first one and break 
        }
    }
    
    //use the YearsAfterFilter to calculate the number of movies in the database 
    //that have at least a minimal number of ratings and 
    //came out in a particular year or later
    public void printAverageRatingsByYearAfter() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of movies in total: " + MovieDatabase.size());
        System.out.println("Number of raters in total: " + tr.getRaterSize());
        //Specify the Filter 
        YearAfterFilter yearAfterFilter = new YearAfterFilter(2000);
        //below is the target avgRatings ArrayList (niminal rater, filter)
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(20, yearAfterFilter);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies:");
        for(Rating r : avgRatings) {
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            Double avgRating = r.getValue();
            System.out.println(avgRating + "\t" + movieTitle);
            break; // print out the first one and break 
        }
    }
    
    public void printAverageRatingsByGenre() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of movies in total: " + MovieDatabase.size());
        System.out.println("Number of raters in total: " + tr.getRaterSize());
        //Specify the Filter 
        GenreFilter genreFilter = new GenreFilter("Comedy");
        //below is the target avgRatings ArrayList (niminal rater, filter)
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(20, genreFilter);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies:");
        for(Rating r : avgRatings) {
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            Double avgRating = r.getValue();
            System.out.println(avgRating + "\t" + movieTitle);
            break; // print out the first one and break 
        }
    }
    
    public void printAverageRatingsByMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of movies in total: " + MovieDatabase.size());
        System.out.println("Number of raters in total: " + tr.getRaterSize());
        //Specify the Filter 
        MinutesFilter minutesFilter = new MinutesFilter(105, 135);
        //below is the target avgRatings ArrayList (niminal rater, filter)
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(5, minutesFilter);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies:");
        for(Rating r : avgRatings) {
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            Double avgRating = r.getValue();
            System.out.println(avgRating + "\t" + movieTitle);
            break; // print out the first one and break 
        }
    }
    
    public void printAverageRatingsByDirectors() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of movies in total: " + MovieDatabase.size());
        System.out.println("Number of raters in total: " + tr.getRaterSize());
        //Specify the Filter 
        DirectorsFilter directorsFilter = 
        new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        //below is the target avgRatings ArrayList (niminal rater, filter)
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(4, directorsFilter);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies:");
        for(Rating r : avgRatings) {
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            Double avgRating = r.getValue();
            String directors = MovieDatabase.getDirector(r.getItem());
            System.out.println(avgRating + "\t" + movieTitle);
            System.out.println("Director(s) are: " + directors);
            break; // print out the first one and break 
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of movies in total: " + MovieDatabase.size());
        System.out.println("Number of raters in total: " + tr.getRaterSize());
        //Specify the Filter(s)
        YearAfterFilter yearAfterFilter = new YearAfterFilter(1990);
        GenreFilter genreFilter = new GenreFilter("Drama");
        //put above filters together and use it in avgRatings
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearAfterFilter);
        allFilters.addFilter(genreFilter);
        //below is the target avgRatings ArrayList (niminal rater, allfilters)
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(8, allFilters);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " matched movies" );
        for(Rating r : avgRatings) {
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            Double avgRating = r.getValue();
            String genres = MovieDatabase.getGenres(r.getItem());
            String directors = MovieDatabase.getDirector(r.getItem());
            System.out.println(avgRating + "\t" + movieTitle);
            System.out.println("Director(s) are: " + directors);
            System.out.println("Genres are: " + genres);
            break; // print out the first one and break 
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Number of movies in total: " + MovieDatabase.size());
        System.out.println("Number of raters in total: " + tr.getRaterSize());
        //Specify the Filter(s)
        DirectorsFilter directorsFilter = 
        new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        MinutesFilter minutesFilter = new MinutesFilter(90, 180);
        //put above filters together and use it in avgRatings
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(directorsFilter);
        allFilters.addFilter(minutesFilter);
        //below is the target avgRatings ArrayList (niminal rater, allfilters)
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(3, allFilters);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " matched movies:" );
        for(Rating r : avgRatings) {
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            Double avgRating = r.getValue();
            int length = MovieDatabase.getMinutes(r.getItem());
            String genres = MovieDatabase.getGenres(r.getItem());
            String directors = MovieDatabase.getDirector(r.getItem());
            System.out.println(avgRating + "\t" + movieTitle);
            System.out.println("Director(s) are: " + directors);
            System.out.println("Genres are: " + genres);
            System.out.println("Length is: " + length + " minutes.");
            break; // print out the first one and break 
        }
    }
}
