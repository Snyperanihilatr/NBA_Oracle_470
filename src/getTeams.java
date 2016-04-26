import java.sql.Connection;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
public class getTeams {
	public static void main (String args[]){
		extractTeams("ChicagoBulls", "9697", true, "LALakers", "0102", false);
	}
	public static void extractTeams(String homeTeam, String homeSeason,boolean oldHome, String awayTeam, String awaySeason, boolean oldAway){
		DBase db = new DBase();
		Connection conn = db.connect(
                "jdbc:mysql://localhost:3306/sys?autoReconnect=true&useSSL=false","root","Gladius7210-O");
		String homeQuery;
		String awayQuery;
		
		homeQuery = "SELECT games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,"
				+ "3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,"
				+ "steals,turnovers,blocks,personal_fouls,points FROM "+homeSeason+
				"rdteam WHERE team = '"+homeTeam+
				"' into OUTFILE 'C:/Windows/temp/homeTotal.csv' FIELDS TERMINATED BY ','";
		db.exportData(conn,homeQuery);
		if(!oldHome){
			homeQuery = "SELECT games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,"
					+ "3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,"
					+ "steals,turnovers,blocks,personal_fouls,points FROM "+homeSeason+
					"homerdteam WHERE team = '"+homeTeam+
					"' into OUTFILE 'C:/Windows/temp/home.csv' FIELDS TERMINATED BY ','";
			db.exportData(conn,homeQuery);
		}
		awayQuery = "SELECT games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,"
				+ "3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,"
				+ "steals,turnovers,blocks,personal_fouls,points FROM "+awaySeason+
				"rdteam WHERE team = '"+awayTeam+
				"' into OUTFILE 'C:/Windows/temp/awayTotal.csv' FIELDS TERMINATED BY ','";
		db.exportData(conn,awayQuery);
		if(!oldAway){
			awayQuery = "SELECT games_won,games_lost,minutes,fg_made,fg_attempts,3s_made,"
					+ "3s_attempts,ft_made,ft_attempts,off_rebounds,tot_rebounds,assists,"
					+ "steals,turnovers,blocks,personal_fouls,points FROM "+awaySeason+
					"awayrdteam WHERE team = '"+awayTeam+
					"' into OUTFILE 'C:/Windows/temp/away.csv' FIELDS TERMINATED BY ','";
			db.exportData(conn,awayQuery);
		}
		Path movefrom = FileSystems.getDefault().getPath("C:/Windows/temp/homeTotal.csv");
        Path target = FileSystems.getDefault().getPath("C:/Users/Oron/Desktop/Oron/Remote/470_CSCE/Project/homeTotal.csv");
        try {
            Files.move(movefrom, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
        if(!oldHome){
        	movefrom = FileSystems.getDefault().getPath("C:/Windows/temp/home.csv");
            target = FileSystems.getDefault().getPath("C:/Users/Oron/Desktop/Oron/Remote/470_CSCE/Project/home.csv");
            try {
                Files.move(movefrom, target, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        movefrom = FileSystems.getDefault().getPath("C:/Windows/temp/awayTotal.csv");
        target = FileSystems.getDefault().getPath("C:/Users/Oron/Desktop/Oron/Remote/470_CSCE/Project/awayTotal.csv");
        try {
            Files.move(movefrom, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
        if(!oldAway){
        	movefrom = FileSystems.getDefault().getPath("C:/Windows/temp/away.csv");
            target = FileSystems.getDefault().getPath("C:/Users/Oron/Desktop/Oron/Remote/470_CSCE/Project/away.csv");
            try {
                Files.move(movefrom, target, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
	}
}
