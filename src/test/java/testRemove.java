import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.DatabaseConnector;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

public class testRemove {

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
    public void testEducationRemove() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;
        try {
            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();

            rs1 = stmt.executeUpdate("insert into Education(person_id) VALUES ('1')");
            rs1 = stmt.executeUpdate("insert into Education(person_id) VALUES ('2')");
            rs1 = stmt.executeUpdate("delete from Education where person_id='1'");
            rs = stmt.executeQuery("select person_id from Education where not person_id = '1'");


            assertTrue(rs.next());
            assertNotEquals("1", rs.getString("person_id"));

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
    public void testExperienceRemove() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;
        try {
            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();

            rs1 = stmt.executeUpdate("insert into Experience(person_id) VALUES ('1')");
            rs1 = stmt.executeUpdate("insert into Experience(person_id) VALUES ('2')");
            rs1 = stmt.executeUpdate("delete from Experience where person_id='1'");
            rs = stmt.executeQuery("select person_id from Experience where not person_id = '1'");

            assertTrue(rs.next());

            assertNotEquals("1", rs.getString("person_id"));
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
    public void testInterestRemove() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;
        try {
            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();

            rs1 = stmt.executeUpdate("insert into Person(interests) VALUES ('asd')");
            rs1 = stmt.executeUpdate("insert into Person(interests) VALUES ('asdf')");
            rs1 = stmt.executeUpdate("delete from Person where interests='asd'");
            rs = stmt.executeQuery("select interests from Person where interests ='asdf'");


            assertTrue(rs.next());
            assertNotEquals("asd", rs.getString("interests"));


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
    public void testPublicationRemove() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;
        try {
            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();

            rs1 = stmt.executeUpdate("insert into Publication(person_id) VALUES ('1')");
            rs1 = stmt.executeUpdate("insert into Publication(person_id) VALUES ('2')");
            rs1 = stmt.executeUpdate("delete from Publication where person_id='1'");
            rs = stmt.executeQuery("select person_id from Publication where person_id ='2'");


            assertTrue(rs.next());
            assertNotEquals("1", rs.getString("person_id"));


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
    public void testSkillRemove() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rs1;
        try {
            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();

            rs1 = stmt.executeUpdate("insert into Person(skills) VALUES ('asd')");
            rs1 = stmt.executeUpdate("insert into Person(skills) VALUES ('qwe')");
            rs1 = stmt.executeUpdate("delete from Person where skills='asd'");
            rs = stmt.executeQuery("select skills from Person where skills ='qwe'");


            assertTrue(rs.next());
            assertNotEquals("asd", rs.getString("skills"));


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
