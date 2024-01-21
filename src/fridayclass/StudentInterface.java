
package za.ac.tut;

import java.sql.SQLException;
import java.util.List;

/**
 * @author HOME
 */
public interface StudentInterface {
    public Boolean add(Student student) throws SQLException;
   
    public Boolean delete(String name) throws SQLException;
    public Boolean update(String name, Double weight) throws SQLException;
    public Student get(String name)  throws SQLException;
    public List<Student> getAll() throws SQLException;
}
