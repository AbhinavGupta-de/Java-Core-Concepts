package org.abhinavgpt.databaseconnection;

public class DatabaseConnection {

    private static DatabaseConnection instance = null;
    private final String connectionUrl;
    private Integer integerPollSize = 8;

    private DatabaseConnection(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    private DatabaseConnection(String connectionUrl, Integer integerPollSize) {
        this.connectionUrl = connectionUrl;
        this.integerPollSize = integerPollSize;
    }

    /**
     * This method is a static factory method that returns an instance of the class.
     *
     * @Param connectionUrl: The connection URL to the database.
     * @return: An instance of the DatabaseConnection class.
     */
    public static DatabaseConnection createConnection(String connectionUrl) {
        if (instance == null) {
            instance = new DatabaseConnection(connectionUrl);
        }
        return instance;
    }

    public static DatabaseConnection createConnectionWithPoolSize(String connectionUrl, Integer poolSize) {
        return new DatabaseConnection(connectionUrl, poolSize);
    }

    @Override
    public String toString() {
        return "DatabaseConnection{" +
                "connectionUrl='" + connectionUrl + '\'' +
                ", integerPollSize=" + integerPollSize +
                '}';
    }

}
