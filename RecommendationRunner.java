
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender{
    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> randomMovies = new ArrayList<String>();
        int numToDisplay = 10;
        int minimalRaters = 5;
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        Random rand = new Random();
        
        for(int i = 0; i < numToDisplay; i++){
            int r = rand.nextInt(movies.size());
            String movieID = movies.get(r);
            if (!randomMovies.contains(movieID)) {
                randomMovies.add(movieID);
            }
        }
        return randomMovies;
    }
    
    @Override
    public void printRecommendationsFor (String webRaterID) {    
        try{
            FourthRatings fr = new FourthRatings();
            ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID,1,1);
            int length = 10; 
        
            if(ratings.size()<10){
                length = ratings.size();
            }
            
            System.out.println
            (
            "<style>"                                                                         +
            "  body { background-color: slateblue; }"                                           +
            "  h2.error { background-color: grey; color: #dc143c; margin: 5; }"            +
            "  #customers, h2 { font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;" +
            "                   border-collapse: collapse; width: 100%;text-align: center;}"  +
            "  #custmers td, #customers th, h2 { border: 1px solid #3e3a3a; padding: 8px;}"   +
            "  #customers tr { background-color: purple; color: #efefef; }"                  +
            "  #customers tr:nth-child(even) { background-color: purple; }"                  +
            "  #customers tr:hover { background-color: violet; }"                            +
            "  #customers th { padding-top: 12px; padding-bottom: 12px; text-align: center;"  +
            "                  background-color: purple; color: white; }"                    +
            "  #customers img { height: 50%; }"                                               +
            "  h2 { background-color: orchid; }"                                             +
            "</style>"                                                                        +
            "<div class=\"content\">"                                                         +
            "  <div class=\"ui-widget\">"                                                     +
            "   <html>"                                                                       +
            "<h2>BGT Flix - These are some movies you may like</h2>"                          +
            "<table id=\"customers\">"                                                        +
            "  <tr>"                                                                          + 
            "    <th>#</th>"                                                                  +
            "    <th>Poster</th>"                                                             +
            "    <th>Title</th>"                                                              +
            "    <th>Genre</th>"                                                              +
            "    <th>Year</th>"                                                               +
            "    <th>Time</th>"                                                               +
            "  </tr>"                                                                         +
            "  <tr>"                                                                          
            );
            
            for(int i=0; i< length; i++) {
                int num = i+1;
                System.out.println("    <td>"+num+"</td>");
                System.out.println("    <td><img src=");
                System.out.println(     "\""+MovieDatabase.getPoster(ratings.get(i).getItem())+"\"");
                System.out.println(" /> </td>");
                System.out.println("    <td>"+MovieDatabase.getTitle(ratings.get(i).getItem())+"</td>");
                System.out.println("    <td>"+MovieDatabase.getCountry(ratings.get(i).getItem())+"</td>");
                System.out.println("    <td>"+MovieDatabase.getYear(ratings.get(i).getItem())+"</td>");
                System.out.println("    <td>"+MovieDatabase.getMinutes(ratings.get(i).getItem())+" Minutes"+"</td>");
                System.out.println("  </tr>");
            }
            
        }catch (Exception e) {
            System.out.println("No movie found matched your ratings, please try again in different ratings.");
        }
    }
}
