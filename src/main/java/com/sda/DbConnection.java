package com.sda;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DbConnection {
    private static Connection connection;

    private static final String INSERT = "INSERT INTO persoane VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE persoane SET nume = ?, varsta = ? WHERE cnp = ?";
    private static final String DELETE = "DELETE FROM persoane WHERE cnp = ?";
    private static final String SELECT = "SELECT * FROM persoane";
    private static final String SELECT_SINGLE = "SELECT * FROM persoane WHERE cnp = ?";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS persoane(" +
            "cnp    VARCHAR(15)  NOT NULL PRIMARY KEY, " +
            "nume  VARCHAR(30)   NOT NULL," +
            "varsta  INT UNSIGNED  NOT NULL)";


    public static boolean updatePersoana(Persoana persoana) {
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
                statement.setString(1, persoana.getNume());
                statement.setInt(2, persoana.getVarsta());
                statement.setString(3, persoana.getCnp());

                int updates = statement.executeUpdate();
                return updates > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        System.out.println("Connection was null");
        return false;
    }

    public static void afiseazaPersoane() {
        List<Persoana> persoane = selectPersoane();
        for (Persoana persoana : persoane) {
            System.out.println(persoana.toString());
        }
    }

    public static List<Persoana> selectPersoane() {
        List<Persoana> persoane = new LinkedList<>();
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Persoana persoana = new Persoana(resultSet.getString("nume"),
                            resultSet.getInt("varsta"),
                            resultSet.getString("cnp"));
                    persoane.add(persoana);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection was null");
        }
        return persoane;
    }

    public static Persoana selectPersoana(Persoana persoana) {
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_SINGLE)) {
                statement.setString(1, persoana.getCnp());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String nume = resultSet.getString("nume");
                    String cnp = resultSet.getString("cnp");
                    int varsta = resultSet.getInt("varsta");
                    return new Persoana(nume, varsta, cnp);
                }
                return null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Connection was null");
        return null;
    }

    public static boolean deletePersoana(Persoana persoana) {
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
                statement.setString(1, persoana.getCnp());

                int deletes = statement.executeUpdate();
                return deletes > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        System.out.println("Connection was null");
        return false;
    }

    public static boolean insertPersoana(Persoana persoana) {
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
                statement.setString(1, persoana.getCnp());
                statement.setString(2, persoana.getNume());
                statement.setInt(3, persoana.getVarsta());

                int updates = statement.executeUpdate();
                return updates > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        System.out.println("Connection was null");
        return false;
    }

    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/persoane",
                    "root", "root");
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
