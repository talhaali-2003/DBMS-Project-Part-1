public class quote {
    private int quoteID;
    private double initialPrice;
    private String timeWindow;
    
    public quote() {
        // Default constructor
    }

    public quote(int quoteID, double initialPrice, String timeWindow) {
        this.quoteID = quoteID;
        this.initialPrice = initialPrice;
        this.timeWindow = timeWindow;
    }

	public int getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(int quoteID) {
        this.quoteID = quoteID;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(String timeWindow) {
        this.timeWindow = timeWindow;
    }

}
