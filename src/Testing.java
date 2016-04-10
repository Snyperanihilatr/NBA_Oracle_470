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

		Dataset data = FileHandler.loadDataset(new File("1415RDTeam_t.csv"), 0, ",");
		Dataset dataForClassification = FileHandler.loadDataset(new File("1415RDTeam_t.csv"), 0, ",");

		double correct = 0, wrong = 0;
		
		double rate = 0.0;
		int k = (int) Math.sqrt((double) data.size());
		
		//Construct Knn classifier
		Classifier knn;
		knn = new KNearestNeighbors(k);
		knn.buildClassifier(data);
		
		//Construct SMO Classifier
		SMO smo = new SMO();
		Classifier javasmo = new WekaClassifier(smo);
		javasmo.buildClassifier(data);
		
		/* Counters for correct and wrong predictions. */
		Vector<Integer> predictions = new Vector<Integer>();
		
		/* Classify all instances and check with the correct class values */
		for (Instance inst : dataForClassification) {
		    Integer predictedValueSMO = Integer.parseInt((String) javasmo.classify(inst));

		    Integer realClassValue = Integer.parseInt((String) inst.classValue());
		    System.out.println("PREDICTED: " + predictedValueSMO + "   REAL VALUE: " + realClassValue);
		    if (predictedValueSMO == realClassValue)
		    	correct++;
		    else
		        wrong++;
		    predictions.add(predictedValueSMO);
		}
		
		//Check if SMO prediction equal, if so perform KNN, otherwise output winner
		if (predictions.get(0) == predictions.get(1)) {
			for (Instance inst : dataForClassification) {
				Integer predictedValueKNN = Integer.parseInt((String) knn.classify(inst));
				predictions.add(predictedValueKNN);
			}
			if (predictions.get(2) > predictions.get(3)) {
				System.out.println("Team 1 Wins with value: " + predictions.get(2) + " Team 2 was: " + predictions.get(3));
			} else if (predictions.get(2) < predictions.get(3)) {
				System.out.println("Team 2 Wins with value: " + predictions.get(3) + " Team 2 was: " + predictions.get(2));
			} else {
				System.out.println("They are Equal and are Team 1 = " + predictions.get(0) + " Team 2 = " + predictions.get(1));
			}
		} else if (predictions.get(0) > predictions.get(1)) {
			System.out.println("Team 1 Wins with value: " + predictions.get(0) + " Team 2 was: " + predictions.get(1));
		} else if (predictions.get(0) < predictions.get(1)) {
			System.out.println("Team 2 Wins with value: " + predictions.get(1) + " Team 2 was: " + predictions.get(0));
		}
		
		rate = correct/(correct+wrong);
		System.out.println("Percentage Correct: "+rate);
		
	}

}