import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
 
public class automateExport {
    public static void main(String[] args) {
        DBase db = new DBase();
        Connection conn = db.connect(
                "jdbc:mysql://localhost:3306/sys?autoReconnect=true&useSSL=false","root","*****");
        String total = "(SELECT games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0001RDTeam)"
        		+ "UNION"
        		+ "(select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0203RDTeam) "
        		+ "UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0304RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0405RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0506RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0708RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0809RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0910RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1011RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1112RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1213RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1314RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1415RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1516RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 8889RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 8990RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 9091RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 9192RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 9293RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 9394RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 9495RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 9596RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 9697RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 9798RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 9899RDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 9900RDTeam"
        		+ " into OUTFILE  '"+"C:/Windows/temp/totalTeam.csv"+"' FIELDS TERMINATED BY ',')";
        
        String home = "(SELECT games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0001homeRDTeam)"
        		+ "UNION"
        		+ "(select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0203homeRDTeam) "
        		+ "UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0304homeRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0405homeRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0506homeRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0708homeRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0809homeRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0910homeRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1011homeRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1112homeRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1213homeRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1314homeRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1415homeRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1516homeRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 9900homeRDTeam"
        		+ " into OUTFILE  '"+"C:/Windows/temp/homeTeam.csv"+"' FIELDS TERMINATED BY ',')";
        
        String away = "(SELECT games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0001awayRDTeam)"
        		+ "UNION"
        		+ "(select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0203awayRDTeam) "
        		+ "UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0304awayRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0405awayRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0506awayRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0708awayRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0809awayRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 0910awayRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1011awayRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1112awayRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1213awayRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1314awayRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1415awayRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 1516awayRDTeam)"
        		+ " UNION"
        		+ " (select games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,steals,turnovers,blocks,personal_fouls,points FROM 9900awayRDTeam"
        		+ " into OUTFILE  '"+"C:/Windows/temp/awayTeam.csv"+"' FIELDS TERMINATED BY ',')";
        db.exportData(conn,total);
        db.exportData(conn,home);
        db.exportData(conn,away);
        Path movefrom = FileSystems.getDefault().getPath("C:/Windows/temp/totalTeam.csv");
        Path target = FileSystems.getDefault().getPath("C:/Users/Oron/Desktop/Oron/Remote/470_CSCE/Project/totalTeam.csv");
        try {
            Files.move(movefrom, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
        movefrom = FileSystems.getDefault().getPath("C:/Windows/temp/homeTeam.csv");
        target = FileSystems.getDefault().getPath("C:/Users/Oron/Desktop/Oron/Remote/470_CSCE/Project/homeTeam.csv");
        try {
            Files.move(movefrom, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
        movefrom = FileSystems.getDefault().getPath("C:/Windows/temp/awayTeam.csv");
        target = FileSystems.getDefault().getPath("C:/Users/Oron/Desktop/Oron/Remote/470_CSCE/Project/awayTeam.csv");
        try {
            Files.move(movefrom, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
     
}
 
class DBase {
    public DBase() {
    }
     
    public Connection connect(String db_connect_str, 
            String db_userid, String db_password) {
        Connection conn;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(db_connect_str,
                    db_userid, db_password);
             
        } catch(Exception e) {
            e.printStackTrace();
            conn = null;
        }
        return conn;
    }
     
    public void exportData(Connection conn,String query) {
        Statement stmt;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
             
            //For comma separated file
            stmt.executeQuery(query);
             
        } catch(Exception e) {
            e.printStackTrace();
            stmt = null;
        }
    }
};
