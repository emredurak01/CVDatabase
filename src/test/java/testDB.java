import com.example.cvdatabase.Helpers.Config;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;



public class testDB {
    @Test
    public void testDB() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(Config.DB_URL);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT 1 FROM Person");

            assertTrue(rs.next());
            assertEquals(1, rs.getInt(1));
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {

            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }
    }
}



