import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Form1 extends JFrame {
    private JTable table;
    private JButton sortButton;
    private JButton displayFlight;
    private JButton searchButton;
    private JButton displayText;
    private DefaultTableModel model;
    private JTextArea textArea;
    
    public Form1(Flight[] flights){
        //set Title and size of JFrame
        setTitle("Flight Information");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set background colour of JFrame
        getContentPane().setBackground(Color.LIGHT_GRAY);

        
        //Initialize button
        displayFlight = new JButton("Display Flight");
        sortButton = new JButton("Sort Flights");       
        searchButton = new JButton("Search");
        displayText = new JButton("Display TextArea");

        //Create a label with project detail
        JLabel project = new JLabel("Final assignment: ");
        project.setHorizontalAlignment(SwingConstants.CENTER);
        project.setFont(new Font("Arial",Font.BOLD, 18));
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(textArea);
        displayText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                sortButton.setEnabled(false);
                searchButton.setEnabled(false);
                textArea.setText(""); // Clear the text area

                for (Flight flight : flights) {
                    textArea.append("Airline Name: " + flight.getAirlineName() + "\n" +
                            "Flight Number: " + flight.getFlightNumber() + "\n" +
                            "Flight Origin: " + flight.getFlightOrigin() + "\n" +
                            "Destination Cities: " + flight.getDestinationCities() + "\n" +
                            "Departure Time: " + flight.getDepartureTime() + "\n" +
                            "Arrival Time: " + flight.getArrivalTime() + "\n" +
                            "Distance (km): " + flight.getDistance() + "\n" +
                            "Airfare: " + flight.getAirfare() + "\n\n");
                }

            }
        });
        //ActionListener for the "Display Flight" button
        displayFlight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                sortButton.setEnabled(true);
                searchButton.setEnabled(true);
                //Define column names for the table
                String[] columnNames = {"Airline Name", "Flight Number", "Flight Origin", "Destination Cities",
                                "Departure Time", "Arrival Time", "Distance (km)", "Airfare"};
                //Create data for the table based on Flight Object
                Object[][] data = new Object[flights.length][];
                for(int i=0; i<flights.length;i++){
                    Flight flight = flights[i];
                    data[i] = new Object[]{flight.getAirlineName(), flight.getFlightNumber(),
                        flight.getFlightOrigin(), flight.getDestinationCities(),
                        flight.getDepartureTime(), flight.getArrivalTime(),
                        flight.getDistance(), flight.getAirfare()};}
                //Creating a default table model with data and column names
                model = new DefaultTableModel(data, columnNames);
                //Create a JTable with the created model
                table = new JTable(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                //set font style for table data
                Font tableFont = new Font("Arial",Font.PLAIN,16);
                table.setFont(tableFont);

                //Set preferred width for each column in the table
                for (int i = 0; i < table.getColumnCount(); i++) {
                    table.getColumnModel().getColumn(i).setPreferredWidth(150); 
                }

                
                JScrollPane scrollPane = new JScrollPane(table);
                getContentPane().add(scrollPane, BorderLayout.CENTER);
                revalidate(); // Refresh the frame after adding the table
                
                //Action Listener for the "Sort Flights" button
                sortButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Sort flights based on flight number when the button is clicked
                    Arrays.sort(flights, Comparator.comparing(Flight::getFlightNumber));
                    displayFlights(flights);
                }
            
            });
                
            }
        });
        //Creating copy of flights
        Flight[] flightCopy = Arrays.copyOf(flights, flights.length);
        //Action Listener for the "Search" button
            searchButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    //Prompt user for input and search for a Flight by Flight Number
                    String searchTerm = JOptionPane.showInputDialog("Enter Flight number to search: ");
                    if (searchTerm != null && !searchTerm.trim().isEmpty()){
                        Flight foundFlight = searchFlightByNumber(flightCopy,searchTerm);
                        if(foundFlight !=null){
                            displayFlights(new Flight[]{foundFlight});
                        
                        } else {
                            JOptionPane.showMessageDialog(null, "Flight not found!","Search Result", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "invalid input.");
                    }
                }
            });
            
            // Create a scroll pane to hold the table
            JScrollPane scrollPane = new JScrollPane(table);
            //Create a JPane to hold button
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(project);
            buttonPanel.add(displayFlight);
            buttonPanel.add(sortButton);
            buttonPanel.add(searchButton);
            buttonPanel.add(displayText);

            //Set layout for the JFrame and add components
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.NORTH);
            setLocationRelativeTo(null); // Centre the JFrame on the screen
            getContentPane().add(scrollPane2, BorderLayout.CENTER);
            
        }

        //methods to display flights in table
        private void displayFlights(Flight[] flights) {
            model.setRowCount(0); // Clear existing data from the table
            for (Flight flight : flights) {
                model.addRow(new Object[]{
                        flight.getAirlineName(),
                        flight.getFlightNumber(),
                        flight.getFlightOrigin(),
                        flight.getDestinationCities(),
                        flight.getDepartureTime(),
                        flight.getArrivalTime(),
                        flight.getDistance(),
                        flight.getAirfare()
                });
            }
        }
        //methods to search for a flight by flight number
        private Flight searchFlightByNumber(Flight[] flights, String flightNumber){
            for (Flight flight:flights){
                if(flight.getFlightNumber().equalsIgnoreCase(flightNumber)){
                    return flight;
                }
            }
            return null; //Flight not found
        }

        
        
        public static void main(String[] args){
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter username: ");
            String username = scan.nextLine();

            System.out.print("Enter password: ");
            String password = scan.nextLine();
            if(FlightTest.authenticate(username, password)){
                System.out.println("Authentication successful. Access granted");
                //Create Flight objects
                Flight[] flights = FlightTest.createFlights();

                //Create and display the form1 GUI
                SwingUtilities.invokeLater(new Runnable() {
                public void run(){
                    Form1 form = new Form1(flights);
                    form.setVisible(true);
                }
            });
            } else{
                System.out.println("Authentication failed. Access denied.");
            }
            }
            
        }
    
