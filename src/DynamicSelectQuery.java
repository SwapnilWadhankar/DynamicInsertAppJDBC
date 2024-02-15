import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DynamicSelectQuery {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Scanner scanner = null;
        ResultSet resultset = null;
        int sid = 0;

        try {
            connection = JDBCutil.getJdbcConnection();
            String sqlSelectQuery = "select * from IPLteam where sid=?";
            if(connection != null){
                pstmt = connection.prepareStatement(sqlSelectQuery);
            }
            if(pstmt != null){
                scanner = new Scanner(System.in);
                System.out.println("Please enter sid of the player :: ");
                sid = scanner.nextInt();

                pstmt.setInt(1,sid);

                System.out.println(sqlSelectQuery);

                //execute the query
                resultset = pstmt.executeQuery();


            }
            if(resultset != null) {
                if (resultset.next()) {
                    System.out.println("SID\t SNAME\t SAGE SADDRESS");
                    System.out.println(resultset.getInt(1) + "\t" + resultset.getString(2) + "\t" + resultset.getInt(3) + "\t" + resultset.getString(4));
                } else {
                    System.out.println("There is no record found with given SID ");
                }
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
