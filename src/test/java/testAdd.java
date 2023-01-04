import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.DatabaseConnector;
import com.example.cvdatabase.Model.Experience;
import com.example.cvdatabase.Model.Person;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

public class testAdd {


    private static Connection connection = null;
    File file = new File(Config.DB_FILE_NAME);
    boolean firstRun = !file.exists();

    public static Connection getInstance() {

        if (connection == null) {
            DatabaseConnector dbConnector = new DatabaseConnector();
            connection = dbConnector.connectDB();
        }

        return connection;

    }

    @Test
    public void testEducationAdd() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;


        try {

            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();

            rs1 = stmt.executeUpdate("insert into Education(person_id) VALUES ('1')");
            rs1 = stmt.executeUpdate("insert into Education(school_name) VALUES ('IEU')");
            rs1 = stmt.executeUpdate("insert into Education(start_date) VALUES ('1')");
            rs1 = stmt.executeUpdate("insert into Education(end_date) VALUES ('1')");
            rs = stmt.executeQuery("select school_name from Education where school_name = 'IEU'");


            assertTrue(rs.next());
            assertEquals("IEU", rs.getString("school_name"));


        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            fail(e.getMessage());
        }


    }

    @Test
    public void testExperienceAdd() {
        Person person = new Person();
        person.setId(0);

        Experience experience = new Experience();
        experience.setTitle("experience");
        experience.setStartDate("date1");
        experience.setEndDate("date2");

        String q = "insert into experience(person_id,title,start_date,end_date) values(?,?,?,?)";

        try {

            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setInt(1, 0);
            ps.setString(2, experience.getTitle());
            ps.setString(3, experience.getStartDate());
            ps.setString(4, experience.getEndDate());

            String r = "select * from experience where person_id = ?";
            PreparedStatement ps1 = DatabaseConnector.getInstance().prepareStatement(r);
            ps1.setInt(1, person.getId());
            ResultSet rs = ps1.executeQuery();

            while (rs.next()) {
                assertEquals(person.getId(), rs.getInt("person_id"));
                assertEquals(person.getExperiences().get(0).getTitle(), rs.getString("title"));
                assertEquals(person.getExperiences().get(0).getStartDate(), rs.getString("start_date"));
                assertEquals(person.getExperiences().get(0).getEndDate(), rs.getString("end_date"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInterestAdd() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;


        try {

            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();

            rs1 = stmt.executeUpdate("insert into Person(interests) VALUES ('asd')");

            rs = stmt.executeQuery("select interests from Person where interests = 'asd'");


            assertTrue(rs.next());
            assertEquals("asd", rs.getString("interests"));


        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            fail(e.getMessage());
        }


    }

    @Test
    public void testPublicationAdd() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;


        try {

            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();

            rs1 = stmt.executeUpdate("insert into Publication(person_id) VALUES ('1')");
            rs1 = stmt.executeUpdate("insert into Publication(title) VALUES ('IEU')");
            rs1 = stmt.executeUpdate("insert into Publication(publisher) VALUES ('1')");
            rs1 = stmt.executeUpdate("insert into Publication(publication_date) VALUES ('1')");
            rs = stmt.executeQuery("select title from Publication where title = 'IEU'");


            assertTrue(rs.next());
            assertEquals("IEU", rs.getString("title"));


        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSkillAdd() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;


        try {

            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();

            rs1 = stmt.executeUpdate("insert into Person(skills) VALUES ('asd')");

            rs = stmt.executeQuery("select skills from Person where skills = 'asd'");


            assertTrue(rs.next());
            assertEquals("asd", rs.getString("skills"));


        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            fail(e.getMessage());
        }


    }


}



