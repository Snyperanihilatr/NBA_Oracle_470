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

		Dataset dataTotal = FileHandler.loadDataset(new File("totalTeam.csv"), 0, ",");
		Dataset dataAway = FileHandler.loadDataset(new File("awayTeam.csv"), 0, ",");
		Dataset dataHome = FileHandler.loadDataset(new File("homeTeam.csv"), 0, ",");
		Dataset dataForAwayTeamTotal = FileHandler.loadDataset(new File("away.csv"), 0, ",");
		Dataset dataForHomeTeamTotal = FileHandler.loadDataset(new File("home.csv"), 0, ",");
		double correct = 0, wrong = 0;
		
		int homePrediction = -1;
		int awayPrediction = -1;
		
		double rate = 0.0;
		int k = (int) Math.sqrt((double) dataTotal.size());
		
		//Construct Knn classifier
		Classifier knn;
		knn = new KNearestNeighbors(k);
		knn.buildClassifier(dataTotal);
		
		//Construct SMO Classifier for Total Data
		SMO smoTotal = new SMO();
		Classifier javasmoTotal = new WekaClassifier(smoTotal);
		javasmoTotal.buildClassifier(dataTotal);
		
		//Construct SMO Classifier for Away Data
		SMO smoAway = new SMO();
		Classifier javasmoAway = new WekaClassifier(smoAway);
		javasmoAway.buildClassifier(dataAway);
		
		//Construct SMO Classifier for Away Data
		SMO smoHome = new SMO();
		Classifier javasmoHome = new WekaClassifier(smoHome);
		javasmoHome.buildClassifier(dataHome);		
		/* Counters for correct and wrong predictions. */
		
		for (Instance inst : dataForHomeTeamTotal) {
			Integer predictedValueSMO = Integer.parseInt((String) javasmoTotal.classify(inst));
			homePrediction = predictedValueSMO;
		}
		
		for (Instance inst : dataForAwayTeamTotal) {
			Integer predictedValueSMO = Integer.parseInt((String) javasmoAway.classify(inst));
			awayPrediction = predictedValueSMO;
		}
		System.out.println("TOTAL SMO = Predicted Home Team: " + homePrediction + "   Predicted Away Team: " + awayPrediction);
		
		for (Instance inst : dataForAwayTeamTotal) {
			Integer predictedValueSMO = Integer.parseInt((String) javasmoTotal.classify(inst));
			awayPrediction = predictedValueSMO;
		}
		
		System.out.println("TOTAL SMO = Predicted Home Team: " + homePrediction + "   Predicted Away Team: " + awayPrediction);
		
		for (Instance inst : dataForHomeTeamTotal) {
			Integer predictedValueSMO = Integer.parseInt((String) knn.classify(inst));
			homePrediction = predictedValueSMO;
		}
		
		for (Instance inst : dataForAwayTeamTotal) {
			Integer predictedValueSMO = Integer.parseInt((String) knn.classify(inst));
			awayPrediction = predictedValueSMO;
		}
		
		System.out.println("TOTAL KNN = Predicted Home Team: " + homePrediction + "   Predicted Away Team: " + awayPrediction);
		
	}

}