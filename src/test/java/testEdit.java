import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.DatabaseConnector;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;


public class testEdit {
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
    public void testEducationEdit() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;
        try {
            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();

            rs1 = stmt.executeUpdate("insert into Education(person_id) VALUES ('1')");
            rs1 = stmt.executeUpdate("update Education set person_id='2' where person_id=1");
            rs = stmt.executeQuery("select person_id from Education where person_id = '2'");
            assertTrue(rs.next());
            assertEquals("2", rs.getString("person_id"));
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
    public void testExperienceEdit() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;
        try {
            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();

            rs1 = stmt.executeUpdate("insert into Experience(person_id) VALUES ('1')");
            rs1 = stmt.executeUpdate("update Experience set person_id='2' where person_id=1");
            rs = stmt.executeQuery("select person_id from Experience where person_id = '2'");
            assertTrue(rs.next());
            assertEquals("2", rs.getString("person_id"));
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
    public void testInterestEdit() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;


        try {

            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();

            rs1 = stmt.executeUpdate("insert into Person(interests) VALUES ('asd')");
            rs1 = stmt.executeUpdate("update Person set interests='asdf' where interests='asd'");
            rs = stmt.executeQuery("select interests from Person where interests ='asdf'");


            assertTrue(rs.next());
            assertEquals("asdf", rs.getString("interests"));


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
    public void testPublicationEdit() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;


        try {

            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();
            rs1 = stmt.executeUpdate("insert into Publication(person_id) VALUES ('1')");
            rs1 = stmt.executeUpdate("insert into Publication(title) VALUES ('qwe')");
            rs1 = stmt.executeUpdate("insert into Publication(publisher) VALUES ('asd')");
            rs1 = stmt.executeUpdate("insert into Publication(publication_date) VALUES ('01.01.0111')");
            rs1 = stmt.executeUpdate("update Publication set person_id=2 where person_id=1");
            rs = stmt.executeQuery("select person_id from Publication where person_id ='2'");


            assertTrue(rs.next());
            assertEquals("2", rs.getString("person_id"));


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
    public void testSkillEdit() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;


        try {

            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();

            rs1 = stmt.executeUpdate("insert into Person(skills) VALUES ('asd')");
            rs1 = stmt.executeUpdate("update Person set skills='asdf' where skills='asd'");
            rs = stmt.executeQuery("select skills from Person where skills ='asdf'");


            assertTrue(rs.next());
            assertEquals("asdf", rs.getString("skills"));


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