import java.io.File;
import java.io.IOException;
import java.util.*;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.classification.bayes.NaiveBayesClassifier;
import net.sf.javaml.filter.discretize.EqualWidthBinning;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;
import net.sf.javaml.tools.weka.WekaClassifier;
import weka.classifiers.functions.SMO;
public class Testing {
	
	
	public static void main(String[] args) throws IOException {
		//Variable to set whether per team home/away comparison performed
		boolean performAwayHomeTest = true;
		
		//Variables to store results of classifications
		int totalHomeSOM = 0;
		int totalAwaySOM = 0;
		int totalHomeKNN = 0;
		int totalAwayKNN = 0;
		int homeHomeSOM = 0;
		int awayAwaySOM = 0;
		int homeHomeKNN = 0;
		int awayAwayKNN = 0;
		
		//Prediction Total Variables are Sum of all variables above
		int totalHomePrediction = -1;
		int totalAwayPrediction = -1;
		
		//Training Dataset Files
		File totalTeam = new File("totalTeam.csv");
		File awayTotalTeam = new File("awayTeam.csv");
		File homeTotalTeam = new File("homeTeam.csv");
		
		//Checks all files to make sure they exist
		if (totalTeam.exists()) {}
			//DO NOTHING
		else
			System.out.println("CRITICAL ERROR: TOTAL TEAM DATA DOES NOT EXIST");
		
		if (awayTotalTeam.exists()) {}
			//DO NOTHING
		else
			System.out.println("CRITICAL ERROR: TOTAL AWAY DATA DOES NOT EXIST");
		
		if (homeTotalTeam.exists()) {}
			//DO NOTHING
		else
			System.out.println("CRITICAL ERROR: TOTAL HOME DATA DOES NOT EXIST");
		
		//Training Datasets
		Dataset dataTotal = FileHandler.loadDataset(totalTeam, 0, ",");
		Dataset dataAway = FileHandler.loadDataset(awayTotalTeam, 0, ",");
		Dataset dataHome = FileHandler.loadDataset(homeTotalTeam, 0, ",");
		
		//Team Dataset Files
		File totalHomeTeam = new File("homeTotal.csv");
		File totalAwayTeam = new File("awayTotal.csv");
		File awayTeamAway = new File("away.csv");
		File homeTeamHome = new File("home.csv");
		
		//Checks all files to make sure they exist
		if (totalHomeTeam.exists()) {}
			//DO NOTHING
		else
			System.out.println("CRITICAL ERROR: TOTAL HOME TEAM DATA DOES NOT EXIST");
		
		if (totalAwayTeam.exists()) {}
			//DO NOTHING
		else
			System.out.println("CRITICAL ERROR: TOTAL AWAY TEAM DATA DOES NOT EXIST");
		
		if (awayTeamAway.exists() && homeTeamHome.exists()) {}
			//DO NOTHING
		else
			performAwayHomeTest = false;
		
		//Team Datasets
		Dataset dataForAwayTeamTotal = FileHandler.loadDataset(new File("awayTotal.csv"), 0, ",");
		Dataset dataForHomeTeamTotal = FileHandler.loadDataset(new File("homeTotal.csv"), 0, ",");
		
		Dataset dataForAwayTeamAway;
		Dataset dataForHomeTeamHome;
		if (performAwayHomeTest) {
			dataForAwayTeamAway = FileHandler.loadDataset(new File("away.csv"), 0, ",");
			dataForHomeTeamHome = FileHandler.loadDataset(new File("home.csv"), 0, ",");
		}
		

		
		int k = (int) Math.sqrt((double) dataTotal.size());
		
		//Construct Knn classifier for Total Data
		Classifier knnTotal;
		knnTotal = new KNearestNeighbors(k);
		knnTotal.buildClassifier(dataTotal);
		
		//Construct Knn classifier for Away Data
		Classifier knnAway;
		knnAway = new KNearestNeighbors(k);
		knnAway.buildClassifier(dataAway);
		
		//Construct Knn classifier for Home Data
		Classifier knnHome;
		knnHome = new KNearestNeighbors(k);
		knnHome.buildClassifier(dataHome);
		
		//Construct SMO Classifier for Total Data
		SMO smoTotal = new SMO();
		Classifier javasmoTotal = new WekaClassifier(smoTotal);
		javasmoTotal.buildClassifier(dataTotal);
		
		//Construct SMO Classifier for Away Data
		SMO smoAway = new SMO();
		Classifier javasmoAway = new WekaClassifier(smoAway);
		javasmoAway.buildClassifier(dataAway);
		
		//Construct SMO Classifier for Home Data
		SMO smoHome = new SMO();
		Classifier javasmoHome = new WekaClassifier(smoHome);
		javasmoHome.buildClassifier(dataHome);		
		/* Counters for correct and wrong predictions. */
		
		//Classify Total Data: SMO first followed by KNN
		for (Instance inst : dataForHomeTeamTotal) {
			Integer predictedValueSMO = Integer.parseInt((String) javasmoTotal.classify(inst));
			totalHomeSOM = predictedValueSMO;
		}
		
		for (Instance inst : dataForAwayTeamTotal) {
			Integer predictedValueSMO = Integer.parseInt((String) javasmoTotal.classify(inst));
			totalAwaySOM = predictedValueSMO;
		}

		for (Instance inst : dataForHomeTeamTotal) {
			Integer predictedValueSMO = Integer.parseInt((String) knnTotal.classify(inst));
			totalHomeKNN = predictedValueSMO;
		}
		
		for (Instance inst : dataForAwayTeamTotal) {
			Integer predictedValueSMO = Integer.parseInt((String) knnTotal.classify(inst));
			totalAwayKNN = predictedValueSMO;
		}
		
		
		
		//If we have away and home data, perform these classifications as well
		if (performAwayHomeTest) {
			System.out.println("PERFORMING AWAY HOME TEST");
			//Test away team's away data on AWAY TOTAL SOM
			for (Instance inst : dataForAwayTeamTotal) {
				Integer predictedValueSMO = Integer.parseInt((String) javasmoAway.classify(inst));
				awayAwaySOM = predictedValueSMO;
			}
			
			//Test home team's home data on HOME TOTAL SOM
			for (Instance inst : dataForHomeTeamTotal) {
				Integer predictedValueSMO = Integer.parseInt((String) javasmoHome.classify(inst));
				homeHomeSOM = predictedValueSMO;
			}
			
			//Test away team's away data on AWAY TOTAL KNN
			for (Instance inst : dataForAwayTeamTotal) {
				Integer predictedValueSMO = Integer.parseInt((String) knnAway.classify(inst));
				awayAwayKNN = predictedValueSMO;
			}
			
			//Test home team's home data on HOME TOTAL KNN
			for (Instance inst : dataForHomeTeamTotal) {
				Integer predictedValueSMO = Integer.parseInt((String) knnHome.classify(inst));
				homeHomeKNN = predictedValueSMO;
			}
		}
		
		totalHomePrediction = (homeHomeSOM + homeHomeKNN + totalHomeKNN + totalHomeSOM);
		totalAwayPrediction = (awayAwaySOM + awayAwayKNN + totalAwayKNN + totalAwaySOM);
		
		if (totalHomePrediction > totalAwayPrediction) {
			System.out.println("HOME TEAM WINS WITH: " + totalHomePrediction + " vs. " + totalAwayPrediction);
			
		} else if (totalAwayPrediction > totalHomePrediction) {
			System.out.println("AWAY TEAM WINS"+ totalAwayPrediction + " vs. " + totalHomePrediction);
			
		} else {
			System.out.println("EFFECTIVELY A TIE");
		}
		
	}

}