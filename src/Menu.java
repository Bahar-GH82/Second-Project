import  java.util.Scanner;
import  java.util.ArrayList;
public class Menu {
    public static int printMenu(){
        Scanner input=new Scanner(System.in);
        System.out.println("""
                              ::::::::::::::::::::::::::::::::::::::::::::
                                 WELCOME TO AIRLINE RESERVATION SYSTEM   \s
                              ::::::::::::::::::::::::::::::::::::::::::::
                               .............. MENU OPTIONS ..............
                                 <1> Sign in
                                 <2> Sign up""");
        int ans=input.nextInt();
        if (ans!=1 && ans!=2){
            System.out.println("try again");
            ans=input.nextInt();
        }
        return ans;
    }

    //This method is the main method for running the program.
    public static void userAndPassword(){
        Scanner input=new Scanner(System.in);
        Passenger[] passengers=new Passenger[1000];
        int flag=0,nouFound=0;
        String userName,password;
        int ans=printMenu();

        while (true){
            if (ans==2){
                passengers [flag]=new Passenger();
                System.out.println("Your username:");
                userName=input.nextLine();
                passengers[flag].setUserName(userName);

                System.out.println("Your password:");
                password=input.nextLine();
                passengers[flag].setPassword(password);
                flag++;
                System.out.println("Okay!");
                ans=printMenu();

            } else if (ans==1) {
                System.out.println("Your username:");
                userName = input.nextLine();
                System.out.println("Your password:");
                password = input.nextLine();

                if (userName.equals("Admin") && password.equals("Admin")) {
                    adminOptions(adminMenu());
                } else {
                    for (int i = 0; i < passengers.length; i++) {
                        if (passengers[i] != null) {
                            System.out.println(passengers[i].getUserName() + passengers[i].getPassword());
                            if (passengers[i].getPassword().equals(password) && passengers[i].getUserName().equals(userName)) {
                                passengerAnswers(passengerMenu());
                                nouFound++;
                            }
                        }
                    }
                    if (nouFound == 0) {
                        System.out.println("not found");
                    }
                }
            }
            else {
                System.out.println("TRY AGAIN:");
                ans=printMenu();
            }
        }
    }

    public static int adminMenu(){
        Scanner input=new Scanner(System.in);
        System.out.println("""
                              :::::::::::::::::::::::::::::::::::
                                     ADMIN MENU OPTIONS
                              :::::::::::::::::::::::::::::::::::
                               .................................
                                  <1> Add
                                  <2> Update
                                  <3> Remove
                                  <4> Flight schedules
                                  <5> Sign out""");
        int ans=input.nextInt();
        if (ans!=1 && ans!=2 && ans!=3 && ans!=4 && ans!=5){
            System.out.println("try again");
            ans=input.nextInt();
        }
        return ans;
    }

    public static void adminOptions(int ans){
        Flights flights=new Flights();
        Scanner input=new Scanner(System.in);
        while (ans!=5){
            switch (ans){
                case 1:
                    flights.addFlight();
                    break;
                case 2:
                    flights.updateFlight();
                    break;
                case 3:
                    flights.removeFlights();
                    break;
                case 4:
                    flights.flights();
                    break;
                default:
                    System.out.println("please try again:");
                    adminMenu();
                    break;
            }
            ans=adminMenu();
        }
        userAndPassword();
    }

    public static int passengerMenu(){
        Scanner input=new Scanner(System.in);
        System.out.println("""
                              ::::::::::::::::::::::::::::::::::::::
                                     PASSENGER MENU OPTIONS
                              ::::::::::::::::::::::::::::::::::::::
                               ....................................
                                  <1> Change password
                                  <2> Search flight tickets
                                  <3> Booking ticket
                                  <4> Ticket cancellation
                                  <5> Booked tickets
                                  <6> Add charge
                                  <0> Sign out""");
        int ans=input.nextInt();
        if (ans!=1 && ans!=2 && ans!=3 && ans!=4 && ans!=5 && ans!=6 && ans!=0){
            System.out.println("try again");
            ans=input.nextInt();
        }
        return ans;
    }

    public static void passengerAnswers(int ans){
        Passenger passenger=new Passenger();
        Flights flights=new Flights();
        Scanner input=new Scanner(System.in);
        ArrayList<Flight> book=new ArrayList<>();
        ArrayList<Flight> table=new ArrayList<>();
        while (ans!=0) {
            switch (ans) {

                case 1:
                    System.out.println("Please enter new password:");
                    String password = input.nextLine();
                    passenger.changePassword(password);
                    break;
                case 2:
                    flights.searchFlight();
                    break;

                case 3:

                    Flight flight=new Flight("XW-12","Yazd","Tehran","1402-12-10","12:30",700000,51);
                    table.add(flight);
                    flight=new Flight("WZ-15","Mashhad","Ahvaz","1401-12-11","08:00",900000,245);
                    table.add(flight);
                    flight=new Flight("BG-22","Shiraz","Tabriz","1401-12-12","22:30",1100000,12);
                    table.add(flight);
                    flight=new Flight("LS-45","Yazd","Kish","1401-12-13","4:30",850000,140);
                    table.add(flight);
                    flight=new Flight("FX-13","Tehran","Esfahan","1401-12-14","11:00",1000000,50);
                    table.add(flight);
                    flight=new Flight("ZA-38","Tehran","Yazd","1401-12-15","21:00",700000,51);
                    table.add(flight);
                    flight=new Flight("TM-58","Ahvaz","Mashhad","1401-12-16","10:30",900000,245);
                    table.add(flight);
                    flight=new Flight("SD-74","tabriz","Shiraz","1401-12-17","00:00",1100000,12);
                    table.add(flight);
                    flight=new Flight("UP-24","Kish","Yazd","1401-12-18","14:15",850000,140);
                    table.add(flight);
                    flight=new Flight("QR-88","Esfahan","Tehran","1401-12-19","23:00",700000,51);
                    table.add(flight);
                    for (int i = 0; i < table.size(); i++) {
                        System.out.println(table.get(i));
                    }
                    System.out.println("please enter the flightId that you want book:");
                    String flightId=input.nextLine();
                    for (int i = 0; i < table.size(); i++) {
                        Flight flight2 = table.get(i);
                        if (flight2.getFlightId().equals(flightId)) ;
                        {
                            book.add(flight2);
                            flight2.setSeats(flight2.getSeats()-1);
                            System.out.println(book.get(0));
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("flight id that you want to cancel:");
                    String id=input.nextLine();
                    for (int i = 0; i < table.size(); i++) {
                        Flight flight3=table.get(i);
                        if (flight3.getFlightId().equals(id)){
                            book.remove(flight3);
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.println(book.size());
                    for (int i = 0; i < book.size(); i++) {
                        System.out.println(book.get(i));
                    }
                    break;
                case 6:
                    System.out.println("please enter your price to charge:");
                    float charge = input.nextFloat();
                    passenger.addCharge(charge);
                    break;
                default:
                    System.out.println("Please try again:");
                    passengerMenu();
                    break;
            }
            ans=passengerMenu();
        }
        userAndPassword();
    }

}
