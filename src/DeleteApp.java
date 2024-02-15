import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteApp {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Scanner scanner = null;
        int sid =0;

        try {
            connection = JDBCutil.getJdbcConnection();
            String sqlDeleteQuery = "delete from IPLteam where sid=?";
            if(connection != null){
                pstmt = connection.prepareStatement(sqlDeleteQuery);
            }
            if(pstmt != null){
                scanner = new Scanner(System.in);

                System.out.println("Please enter id of the player :: ");
                sid = scanner.nextInt();
                pstmt.setInt(1,sid);
                System.out.println(sqlDeleteQuery);

                //execute the query
                int rowCount = pstmt.executeUpdate();
                System.out.println("Rows updated :: "+rowCount);
            }

        }catch(IOException ie) {
            ie.printStackTrace();
        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCutil.cleanUp(connection, pstmt, null);
                scanner.close();
                System.out.println("Closing the resource...");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
