import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//Creating a driver class FlightTest 
public class FlightTest{
    //Main method to run the application
        public static void main(String[] args){
            //Scanner for user input
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter username: ");
            String username = scan.nextLine();

            System.out.print("Enter password: ");
            String password = scan.nextLine();
            //Authenticating user
            if(authenticate(username, password)){
                System.out.println("Authentication successful. Access granted");
                Flight[] flights = createFlights();
                System.out.println("\nFlight Details:");
                for (Flight flight:flights){
                System.out.println(flight);
                
            }
                Arrays.sort(flights, Comparator.comparing(Flight::getFlightNumber)); //
                System.out.println("\nFlight Details (After sorting)");
                for (Flight flight:flights){
                    System.out.println(flight);
        
        }
            } else {
                System.out.println("Authentication failed. Access denied.");
            }
            
            scan.close();    
    }
        //Array of predefined user credentials
        private static final User[] USERS = {
            new User("admin","admin123"),
            new User("user","user123")  
        };

        //methods to authenticate user based on provided username and password
        public static boolean authenticate(String username,String password){
            for(User user:USERS){
                if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
                }
            }
            return false;
        }

        //Methods to create an array of Flight objects based on user input
        public static Flight[] createFlights(){
            Scanner scan =new Scanner(System.in);
            System.out.print("Enter the number of flights: ");
            int numberOfFlight = scan.nextInt();
            scan.nextLine();

            //Create an array of Flight object
            Flight[] flights = new Flight[numberOfFlight];

            for(int i=0;i<numberOfFlight;i++){
                System.out.println("\nEnter details for Flight "+(i+1)+":");
                System.out.print("Airline Name: ");
                String airlineName = scan.nextLine();
                System.out.print("Flight Number: ");
                String flightNumber = scan.nextLine();
                String flightOrigin="";
                Boolean validFlight=false;
                while(!validFlight){
                    System.out.print("Flight Origin: ");
                    flightOrigin = scan.nextLine();
                    if(!flightOrigin.isEmpty()){
                        if(!flightOrigin.matches("^\\d.*")){
                            validFlight = true;
                        } else {
                            System.out.println("Invalid input. Please start with alphabet.");
                        }
                    } else {
                        System.out.println("Flight Origin cannot be empty. ");
                    }
                }

                String destinationCities="";
                Boolean validDestinationCities=false;
                while(!validDestinationCities){
                    System.out.print("Destination Cities: ");
                    destinationCities = scan.nextLine();
                    if(!destinationCities.isEmpty()){
                        if(!destinationCities.matches("^\\d.*")){
                            validDestinationCities = true;
                        } else {
                            System.out.println("Invalid input. Please start with alphabet.");
                        }
                    } else {
                        System.out.println("Destination Cities cannot be empty. ");
                    }
                }
            
                
                //Validate departure time input
                String departureTime = "";
                boolean validDepartureTime = false;
                while(!validDepartureTime){
                    System.out.print("Departure Time (HH:mm): ");
                    departureTime = scan.nextLine();
                    if (isValidTimeFormat(departureTime)){
                        validDepartureTime = true;
                    } else {
                        System.out.println("Invalid Time Format. Please enter time in HH:mm format");
                    }
                }
                //Validate arrival time input
                String arrivalTime="";
                boolean validArrivalTime = false;
                while(!validArrivalTime){
                    System.out.print("Arrival Time (HH:mm): ");
                    arrivalTime = scan.nextLine();
                    if (isValidTimeFormat(arrivalTime)){

                        validArrivalTime = true;
                    } else {
                        System.out.println("Invalid Time Format. Please enter time in HH:mm format");
                    }
                }
                
                //validate distance input
                double distance = 0;
                boolean validDistance = false;
                while(!validDistance){
                    System.out.print("Distance: ");
                    if(scan.hasNextDouble()){
                        distance = scan.nextDouble();
                        if(distance>0){
                            validDistance = true;
                        } else{
                            System.out.println("Distance must be a positive number.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter numeric value for a distance");
                        scan.next();
                        scan.nextLine();//Consume the newline character
                    }
                }
                scan.nextLine();

                //Validate airfare input
                float airFare = 0;
                boolean validAirFare = false;
                while(!validAirFare){
                    System.out.print("Airfare: ");
                    if(scan.hasNextFloat()){
                        airFare = scan.nextFloat();
                        if (airFare>0){
                            validAirFare=true;
                        } else {
                            System.out.println("Airfare must be a positive number.");
                        }
                } else {
                    System.out.println("Invalid input. Please enter a numeric value.");
                    scan.next();
                }
                scan.nextLine();
            }   
                //Create flight object and add it to the array
                flights[i] = new Flight(airlineName,flightNumber,flightOrigin,destinationCities,airFare,departureTime,arrivalTime,distance);}
                return flights;
            }

        //Method to vlidate time format (HH:mm)
        private static boolean isValidTimeFormat(String time){
            String timeRegex = "([0-2][0-9]:[0-6][0-9])";
            return time.matches(timeRegex);
        }
        
        

}