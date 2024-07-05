package org.abhinavgpt;

import org.abhinavgpt.databaseconnection.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = DatabaseConnection.createConnection("localhost");
        System.out.println(databaseConnection);

        DatabaseConnection databaseConnection2 = DatabaseConnection.createConnectionWithPoolSize("localhost", 10);
        System.out.println(databaseConnection2);

        System.out.println(databaseConnection2 == databaseConnection);

        DatabaseConnection anotherDBConnection = DatabaseConnection.createConnection("aws");
        System.out.println(anotherDBConnection);

        System.out.println(anotherDBConnection == databaseConnection);
        System.out.println(anotherDBConnection == databaseConnection2);
    }
}