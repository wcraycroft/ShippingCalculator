package wcraycroft.cs134.miracosta.edu;

/**
 * Represents an item to be shipped.
 * Takes in a weight in ounces and uses a static formula to calculate shipping cost.
 */
public class ShipItem {

    // Constants
    /** The base cost to ship an item under minimum weight */
    private static final double BASE_COST = 3.0;
    /** The added cost to be multiplied for each weight increment above minimum weight*/
    private static final double ADDED_COST_PER_INCREMENT = 0.5;
    /** The minimum weight the item can be before added costs are applied*/
    private static final int MINIMUM_WEIGHT = 16;
    /** The weight increment at which additional added costs are applied.
     *  Note that weight thresholds are exclusive.*/
    private static final int ADDED_COST_INCREMENT = 4;
    // Member variables
    /** The item's weight in ounces.*/
    private int mWeight;
    /** The item's added costs above base cost.*/
    private double mAddedCost;
    /** The item's total cost (base cost + added cost)*/
    private double mTotalCost;

    /**
     * Creates a new shipping item with a default weight of 0.
     * Setter will instantiate other member variables to appropriate values.
     */
    public ShipItem() {
        setWeight(0);
    }

    /**
     * Gets the item's weight in onces.
     *
     * @return The item's weight.
     */
    public int getWeight() {
        return mWeight;
    }

    /**
     * Gets the item's added costs when it exceeds minimum weight.
     *
     * @return The added cost.
     */
    public double getAddedCost() {
        return mAddedCost;
    }

    /**
     * Gets the item's base cost, a predetermined constant.
     *
     * @return The base cost.
     */
    public double getBaseCost() {
        return BASE_COST;
    }

    /**
     * Gets the item's total cost (base cost + added costs)
     *
     * @return The total cost.
     */
    public double getTotalCost() {
        return mTotalCost;
    }

    /**
     * Changes the item's weight and recalculates it's added and total costs.
     *
     * @param weight The item's new weight.
     */
    public void setWeight(int weight) {
        mWeight = weight;
        calculateCosts();
    }

    /**
     * Calculates the added and total costs based on the current weight.
     * This method is called when ShipItem is instantiated or when setWeight() is called.
     * If weight is below minimum weight, sets total cost to base cost.
     * Because weight thresholds are exclusive, minimum weight is reduced by 1 in the added
     * cost equation prior to integer division.
     */
    private void calculateCosts() {
        mAddedCost = (mWeight > MINIMUM_WEIGHT) ? (((mWeight - MINIMUM_WEIGHT - 1) / ADDED_COST_INCREMENT)
                * ADDED_COST_PER_INCREMENT) + ADDED_COST_PER_INCREMENT : 0.0;
        mTotalCost = BASE_COST + mAddedCost;
    }
}
