package bean;

import java.io.Serializable;

/**
 * Java Bean for Orders
 */
public class Order implements Serializable{
    int cutomerId;
    String firstName;
    String lastName;
    int overallItems;
    int numberOfDivingSuits;
    int numberOfSurfBoards;
    int orderId;
    boolean valid;
    String validationResult;

    public int getCutomerId() {
        return cutomerId;
    }

    public void setCutomerId(int cutomerId) {
        this.cutomerId = cutomerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getOverallItems() {
        return overallItems;
    }

    public void setOverallItems(int overallItems) {
        this.overallItems = overallItems;
    }

    public int getNumberOfDivingSuits() {
        return numberOfDivingSuits;
    }

    public void setNumberOfDivingSuits(int numberOfDivingSuits) {
        this.numberOfDivingSuits = numberOfDivingSuits;
    }

    public int getNumberOfSurfBoards() {
        return numberOfSurfBoards;
    }

    public void setNumberOfSurfBoards(int numberOfSurfBoards) {
        this.numberOfSurfBoards = numberOfSurfBoards;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(String validationResult) {
        this.validationResult = validationResult;
    }

    @Override
    public String toString() {
        return "Order{" +
                "cutomerId=" + cutomerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", overallItems=" + overallItems +
                ", numberOfDivingSuits=" + numberOfDivingSuits +
                ", numberOfSurfBoards=" + numberOfSurfBoards +
                ", orderId=" + orderId +
                ", valid=" + valid +
                ", validationResult='" + validationResult + '\'' +
                '}';
    }
}
