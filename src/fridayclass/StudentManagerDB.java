
package za.ac.tut;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HOME
 */
public class StudentManagerDB implements StudentInterface{
    private Connection connection;

    public StudentManagerDB(String url, String password, String username) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    @Override
    public Boolean add(Student student) throws SQLException {
        String sql = "INSERT INTO STUDENT VALUES (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, student.getName());
        ps.setDouble(2, student.getWeight());
        ps.setString(3, student.getAge());
        ps.setString(4, student.getGender().toString());
        
        ps.executeUpdate();
        ps.close();
        return true;
    }

    @Override
    public Boolean delete(String name) throws SQLException {
        String sql = "DELETE FROM STUDENT WHERE NAME = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.executeUpdate();
        ps.close();
        
        return true;
    }

    @Override
    public Boolean update(String name, Double weight) throws SQLException {
        String sql = "UPDATE STUDENT SET WEIGHT = ? WHERE NAME = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setDouble(1, weight);
        ps.setString(2, name);
        ps.executeUpdate();
        ps.close();
        
        return true;
    }

    @Override
    public Student get(String name) throws SQLException {
        String sql = "SELECT * FROM STUDENT WHERE NAME = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String name1 = rs.getString("NAME");
        Double weight1 = rs.getDouble("WEIGHT");
        String age = rs.getString("AGE");
        Character gender = rs.getString("GENDER").charAt(0);
        Student s1 = new Student(name1, weight1, age, gender);
        return s1;
        }
        return null;
    }

    @Override
    public List<Student> getAll() throws SQLException {
        String sql = "SELECT * FROM STUDENT";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Student> students = new ArrayList<>();
        while (rs.next()) {            
         String name1 = rs.getString("NAME");
        Double weight1 = rs.getDouble("WEIGHT");
        String age = rs.getString("AGE");
        Character gender = rs.getString("GENDER").charAt(0);
        Student s1 = new Student(name1, weight1, age, gender);
        students.add(s1);
        }
        return students;
    }

   
    
}
