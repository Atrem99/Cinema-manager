package CinemaManager;

import java.util.Scanner;

class Main {
    public static Scanner sc = new Scanner(System.in);

    public static int rowsNumber;
    public static int seatsNumber;
    public static int rowNumber;
    public static int seatNumber;
    public static int itemNumber;
    public static double totalSeats;
    public static int countPurchasedPlace = 0;
    public static double percentage;
    public static int currentIncome;
    public static int totalIncome;
    public static int ticket;

    static char freePlace = 'S';
    static char purchasedPlace = 'B';
    static char emptyPlace = ' ';

    public static char[] orderOfPlaces;
    public static char[][] cinemaSite;

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        rowsNumber = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsNumber = sc.nextInt();

        totalSeats = seatsNumber * rowsNumber;

        buildCinema();
        calculateTotalIncome();
        menu();
    }

    public static void buyTicket() {

        while (true) {
            System.out.println("Enter a row number:");
            rowNumber = sc.nextInt();

            System.out.println("Enter a seat number in that row:");
            seatNumber = sc.nextInt();

            if (rowNumber > 9 || rowNumber < 0 || seatNumber > 9 || seatNumber < 0) {
                System.out.println("Wrong input!\n");

            }else if(cinemaSite[rowNumber][seatNumber] == purchasedPlace){
                System.out.println("That ticket has already been purchased! \n");

            } else {
                printPurchasedPlace();
                priceCalculation();
                menu();
            }
        }
    }
    public static void calculateTotalIncome() {
        if(totalSeats <= 60){
            ticket = 10;
            totalIncome = (int) (totalSeats * ticket);
        } else {
            double num =  ((double) rowsNumber / 2);
            double frontRows = Math.floor(num);
            double rearRows = Math.ceil(num);
            totalIncome = (int) ((frontRows * seatsNumber) * 10 + (rearRows * seatsNumber) * 8);
        }
    }

    public static void priceCalculation(){

        if (totalSeats <= 60) {
            ticket = 10;
        } else {
            if (rowNumber <= 4) {
                ticket = 10;
            } else {
                ticket = 8;
            }
        }
        System.out.println("Ticket price: $" + ticket);
        currentIncome += ticket;
        countPurchasedPlace++;
    }

    public static void buildCinema (){
        orderOfPlaces = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        cinemaSite = new char[rowsNumber + 1][seatsNumber + 1];

        for (int i = 0; i < cinemaSite.length - 1; i++) {
            for (int j = 0; j < cinemaSite[i].length - 1; j++) {
                cinemaSite[i + 1][j + 1] = freePlace;
                cinemaSite[i + 1][0] = orderOfPlaces[i];
                cinemaSite[0][j + 1] = orderOfPlaces[j];
            }
        }
    }
    public static void printPurchasedPlace(){
        for (int i = 0; i < cinemaSite.length; i++) {
            for (int j = 0; j < cinemaSite[i].length; j++) {
                cinemaSite[rowNumber][seatNumber] = purchasedPlace;
            }
        }
    }
    public static void printCinema(){
        System.out.println("Cinema:");
        for (int i = 0; i < cinemaSite.length; i++) {
            for (int j = 0; j < cinemaSite[i].length; j++) {
                cinemaSite[0][0] = emptyPlace;
                System.out.print(cinemaSite[i][j]+" ");
            }
            System.out.println();
        }
        menu();
    }

    public static void printStatistics(){

        System.out.println("Number of purchased tickets: " + countPurchasedPlace);

        percentage = (100/totalSeats) * countPurchasedPlace;
        String result = String.format("%.2f",percentage);
        System.out.println("Percentage: " + result + "%");

        System.out.println("Current income: $" + currentIncome);

        System.out.println("Total income: $" + totalIncome);

        menu();
    }

    public static void menu() {
        System.out.println();
        System.out.println("1. Show the seats \n2. Buy a ticket \n3. Statistics \n0. Exit");
        itemNumber = sc.nextInt();
        while (itemNumber != 0) {
            switch (itemNumber) {
                case 1:
                    printCinema();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    printStatistics();
                    break;
                default:
                    System.err.println("Такой цифры не существует");
                    menu();
            }
        }
    }
}
