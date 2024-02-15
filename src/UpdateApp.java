import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateApp {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Scanner scanner = null;
        int sid =0;
        String saddress=null;

        try {
            connection = JDBCutil.getJdbcConnection();
            String sqlUpdateQuery = "update IPLteam set saddress =? where sid =?";
            if(connection != null){
                pstmt = connection.prepareStatement(sqlUpdateQuery);
            }
            if(pstmt != null){

                scanner = new Scanner(System.in);
                System.out.println("Please enter id of the player to be updated :: ");
                sid = scanner.nextInt();

                System.out.println("Please enter the new team for the player :: ");
                saddress = scanner.next();

                pstmt.setInt(2,sid);
                pstmt.setString(1,saddress);
                System.out.println(sqlUpdateQuery);

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
