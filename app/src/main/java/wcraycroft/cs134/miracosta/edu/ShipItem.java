package wcraycroft.cs134.miracosta.edu;

public class ShipItem {
    // Constants
    private static final double BASE_COST = 3.0;
    private static final double ADDED_COST_PER_WEIGHT = 0.5;
    private static final int MINIMUM_WEIGHT = 16;
    private static final int ADDED_COST_WEIGHT = 4;
    // Member variables
    private int mWeight;
    private double mAddedCost;
    private double mTotalCost;

    // Constructor
    public ShipItem() {
        mWeight = 0;
        calculateCosts();
    }

    // Getters
    public int getWeight() {
        return mWeight;
    }

    public double getAddedCost() {
        return mAddedCost;
    }

    public double getBaseCost() {
        return BASE_COST;
    }

    public double getTotalCost() {
        return mTotalCost;
    }

    // Setters
    public void setWeight(int weight) {
        mWeight = weight;
        calculateCosts();
    }

    // Calculate added cost and total cost
    private void calculateCosts() {
        // If over min weight for added costs.
        // Because weight thresholds are exclusive, subtract 1 from weight before int division.
        mAddedCost = (mWeight > MINIMUM_WEIGHT) ? (((mWeight - MINIMUM_WEIGHT - 1) / ADDED_COST_WEIGHT)
                * ADDED_COST_PER_WEIGHT) + ADDED_COST_PER_WEIGHT : 0.0;
        mTotalCost = BASE_COST + mAddedCost;
    }
}
