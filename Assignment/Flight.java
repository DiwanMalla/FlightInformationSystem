import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


//Flight class representing flight information
public class Flight {
    //Instance variables
    private String airlineName;
    private String flightNumber;
    private String flightOrigin;
    private String destinationCities;
    private float airFare;
    private String departureTime;
    private String arrivalTime;
    private double distance;

//Constructor to initialize flight details
    public Flight(String airlineName, String flightNumber, String flightOrigin, String destinationCities,
                float airFare, String departureTime, String arrivalTime, double distance){
        // Initializing instance variables
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
        this.flightOrigin = flightOrigin;
        this.destinationCities = destinationCities;
        this.airFare = airFare;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.distance = distance;
    }

    //getter and setter method for all instance variable
    public String getAirlineName(){
        return airlineName;
    }

    public void setAirlineName(String airlineName){
        this.airlineName = airlineName;
    }

    public String getFlightNumber(){
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber){
        this.flightNumber = flightNumber;
    }

    public String getFlightOrigin(){
        return flightOrigin;
    }

    public void setFlightOrigin(String flightOrigin){
        this.flightOrigin = flightOrigin;
    }

    public String getDestinationCities(){
        return destinationCities;
    }

    public void setDestinationCities(String destinationCities){
        this.destinationCities = destinationCities;
    }

    public Float getAirfare(){
        return airFare;
    }

    public void setAirfare(float airFare){
        this.airFare = airFare;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime){
        this.departureTime = departureTime;
    }

    public String getArrivalTime(){
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public double getDistance(){
        return distance;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }
   
    //CREATE toString() method to return a one-line description of flight details
    public String toString(){
        return airlineName + " Flight " + flightNumber + " from " + flightOrigin + " to " + destinationCities +
        " | Departure: " + departureTime + " | Arrival: " + arrivalTime + " |Distance: " + distance +
        "km | Airfare: $"+airFare;
    }

}
