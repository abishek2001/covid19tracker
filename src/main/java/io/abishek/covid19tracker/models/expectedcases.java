package io.abishek.covid19tracker.models;

import java.io.IOException;

public class expectedcases {
}



public class DecisionTreeDemo {

    public static final String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";



    public static Instances getDataSet(String fileName) throws IOException {

        int classIdx = 1;

        ArffLoader loader = new ArffLoader();

        loader.setSource(DecisionTreeDemo.class.getResourceAsStream("/" + fileName));

        //loader.setFile(new File(fileName));
        Instances dataSet = loader.getDataSet();

        dataSet.setClassIndex(classIdx);
        return dataSet;
    }


    public static void process() throws Exception {

        Instances trainingDataSet = getDataSet(VIRUS_DATA_URL);
        Instances testingDataSet = getDataSet(VIRUS_DATA_URL);

        System.out.println("**J48***");

        Classifier classifier = new J48();
        //J48,Id3

        classifier.buildClassifier(trainingDataSet);

        Evaluation eval = new Evaluation(trainingDataSet);
        eval.evaluateModel(classifier, testingDataSet);

        System.out.println("** Expected Cases **");
        System.out.println(eval.toSummaryString());
        System.out.print(" Men is ");
        System.out.println(classifier);
        System.out.println(eval.toMatrixString());
        System.out.println(eval.toClassDetailsString());

        System.out.println("Women");

        Classifier id3Classifier = new Id3();

        //J48,Id3

        id3Classifier.buildClassifier(trainingDataSet);

        Evaluation evalId3 = new Evaluation(trainingDataSet);
        evalId3.evaluateModel(id3Classifier, testingDataSet);

        System.out.println("** Decision Tress Evaluation with Datasets **");
        System.out.println(evalId3.toSummaryString());
        System.out.print(" the expression for the input data as per alogorithm is ");
        System.out.println(id3Classifier);
        System.out.println(evalId3.toMatrixString());
        System.out.println(evalId3.toClassDetailsString());

    }

}
