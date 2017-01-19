package bean;

import java.io.Serializable;

/**
 * Created by nouman on 1/19/17.
 */
public class Order implements Serializable{
    int cutomerId;
    String firstName;
    String lastName;
    int overallItems;
    int numberOfDivingSuits;
    int numberOfSurfBoards;
    int orderId;

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
                '}';
    }
}
