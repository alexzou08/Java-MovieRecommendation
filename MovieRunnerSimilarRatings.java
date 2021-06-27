
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        System.out.println("Number of movies in total: " + MovieDatabase.size());
        System.out.println("Number of raters in total: " + RaterDatabase.size());
        // below is the target avgRatings ArrayList
        ArrayList<Rating> avgRatings = fr.getAverageRatings(35);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies:");
        for(Rating r : avgRatings) {
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            Double avgRating = r.getValue();
            System.out.println(avgRating + "\t" + movieTitle);
            break; // print out the first one and break 
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        //System.out.println("Number of movies in total: " + MovieDatabase.size());
        //System.out.println("Number of raters in total: " + RaterDatabase.size());
        //Specify the Filter(s)
        YearAfterFilter yearAfterFilter = new YearAfterFilter(1990);
        GenreFilter genreFilter = new GenreFilter("Drama");
        //put above filters together and use it in avgRatings
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearAfterFilter);
        allFilters.addFilter(genreFilter);
        //below is the target avgRatings ArrayList (niminal rater, allfilters)
        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(8, allFilters);
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
    
    public void printSimilarRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        //System.out.println("Number of movies in total: " + MovieDatabase.size());
        //System.out.println("Number of raters in total: " + RaterDatabase.size());
        //Get similar ratings
        //(String rater ID, int number of top similar raters, int minimal raters)
        ArrayList<Rating> ratings = fr.getSimilarRatings("71", 20, 5);
        System.out.println("Number of movies matched: " + ratings.size());
        for(Rating r : ratings) {
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            Double avgRating = r.getValue();
            System.out.println(movieTitle + "\t" + avgRating);
            break; // print out the first one and break
        }
    }
    
    public void printSimilarRatingsByGenre() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        //System.out.println("Number of movies in total: " + MovieDatabase.size());
        //System.out.println("Number of raters in total: " + RaterDatabase.size());
        //Specify the Filter(s)
        //YearAfterFilter yearAfterFilter = new YearAfterFilter(1990);
        //DirectorsFilter directorFilter = new DirectorsFilter("");
        //MinutesFilter minutesFilter = new MinutesFilter(80, 160);
        GenreFilter genreFilter = new GenreFilter("Mystery");
        //put above filters together and use it in avgRatings
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(genreFilter);
        //Get similar ratings by Filter(s);
        //(String rater ID, int number of top similar raters, int minimal raters, Filters)
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("964", 20, 5, allFilters);
        System.out.println("Number of movies matched: " + ratings.size());
        for(Rating r : ratings) {
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            Double avgRating = r.getValue();
            System.out.println(movieTitle + "\t" + avgRating);
            break; // print out the first one and break
        }
    }
    
    public void printSimilarRatingsByDirector() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        //System.out.println("Number of movies in total: " + MovieDatabase.size());
        //System.out.println("Number of raters in total: " + RaterDatabase.size());
        //Specify the Filter(s)
        //YearAfterFilter yearAfterFilter = new YearAfterFilter(1990);
        DirectorsFilter directorFilter = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        //MinutesFilter minutesFilter = new MinutesFilter(80, 160);
        //GenreFilter genreFilter = new GenreFilter("Action");
        //put above filters together and use it in avgRatings
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(directorFilter);
        //Get similar ratings by Filter(s);
        //(String rater ID, int number of top similar raters, int minimal raters, Filters)
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("120", 10, 2, allFilters);
        System.out.println("Number of movies matched: " + ratings.size());
        for(Rating r : ratings) {
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            Double avgRating = r.getValue();
            System.out.println(movieTitle + "\t" + avgRating);
            break; // print out the first one and break
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        //System.out.println("Number of movies in total: " + MovieDatabase.size());
        //System.out.println("Number of raters in total: " + RaterDatabase.size());
        //Specify the Filter(s)
        //YearAfterFilter yearAfterFilter = new YearAfterFilter(1990);
        //DirectorsFilter directorFilter = new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
        MinutesFilter minutesFilter = new MinutesFilter(80, 160);
        GenreFilter genreFilter = new GenreFilter("Drama");
        //put above filters together and use it in avgRatings
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(minutesFilter);
        allFilters.addFilter(genreFilter);
        //Get similar ratings by Filter(s);
        //(String rater ID, int number of top similar raters, int minimal raters, Filters)
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("168", 10, 3, allFilters);
        System.out.println("Number of movies matched: " + ratings.size());
        for(Rating r : ratings) {
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            Double avgRating = r.getValue();
            System.out.println(movieTitle + "\t" + avgRating);
            break; // print out the first one and break
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        //System.out.println("Number of movies in total: " + MovieDatabase.size());
        //System.out.println("Number of raters in total: " + RaterDatabase.size());
        //Specify the Filter(s)
        YearAfterFilter yearAfterFilter = new YearAfterFilter(1975);
        //DirectorsFilter directorFilter = new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
        MinutesFilter minutesFilter = new MinutesFilter(70, 200);
        //GenreFilter genreFilter = new GenreFilter("Adventure");
        //put above filters together and use it in avgRatings
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(minutesFilter);
        allFilters.addFilter(yearAfterFilter);
        //Get similar ratings by Filter(s);
        //(String rater ID, int number of top similar raters, int minimal raters, Filters)
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("314", 10, 5, allFilters);
        System.out.println("Number of movies matched: " + ratings.size());
        for(Rating r : ratings) {
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            Double avgRating = r.getValue();
            System.out.println(movieTitle + "\t" + avgRating);
            break; // print out the first one and break
        }
    }
}
