import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class DynamicInsertApp {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Scanner scanner = null;

        try {
            connection = JDBCutil.getJdbcConnection();
            String sqlInsertQuery = "Insert into IPLTEAM(`sname`,`sage`,`saddress`) values(?,?,?)";
            if(connection != null){
               pstmt = connection.prepareStatement(sqlInsertQuery);
            }
            if(pstmt != null){
                scanner = new Scanner(System.in);
                System.out.println("Please enter the name of the player :: ");
                String sname = scanner.next();
                System.out.println("Please enter age of the player :: ");
                int sage = scanner.nextInt();
                System.out.println("Please enter team of the player :: ");
                String saddress = scanner.next();

                pstmt.setString(1,sname);
                pstmt.setInt(2,sage);
                pstmt.setString(3,saddress);

                System.out.println(sqlInsertQuery);

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
