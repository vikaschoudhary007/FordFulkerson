package FordFulkerson.Graph;

import java.util.ArrayList;
import java.util.List;

public class Result {
    public int numberOfAugmentingPath;
    public double meanLength;
    public double mpl;
    public int totalEdges;

    public Result(int numberOfAugmentingPath, double meanLength, double mpl, int totalEdges) {
        this.numberOfAugmentingPath = numberOfAugmentingPath;
        this.meanLength = meanLength;
        this.mpl = mpl;
        this.totalEdges = totalEdges;
    }

    public int getNumberOfAugmentingPath() {
        return numberOfAugmentingPath;
    }

    public void setNumberOfAugmentingPath(int numberOfAugmentingPath) {
        this.numberOfAugmentingPath = numberOfAugmentingPath;
    }

    public double getMeanLength() {
        return meanLength;
    }

    public void setMeanLength(double meanLength) {
        this.meanLength = meanLength;
    }

    public double getMpl() {
        return mpl;
    }

    public void setMpl(double mpl) {
        this.mpl = mpl;
    }

    public int getTotalEdges() {
        return totalEdges;
    }

    public void setTotalEdges(int totalEdges) {
        this.totalEdges = totalEdges;
    }

    public static void printResult(Result result) {
        System.out.println("Number of Augmenting Paths: " + result.getNumberOfAugmentingPath());
        System.out.println("Mean Length: " + result.getMeanLength());
        System.out.println("Maximum Path Length: " + result.getMpl());
        System.out.println("Total Edges: " + result.getTotalEdges());
    }

}
