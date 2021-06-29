
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (Jiaqi Zou) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String[] myDirectors;
    
    public DirectorsFilter(String directors) {
        myDirectors = directors.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        for(String director : myDirectors) {
            if(MovieDatabase.getDirector(id).contains(director)) {
                return true;
            }
        }
        return false;
    }
}
