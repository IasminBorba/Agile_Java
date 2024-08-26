package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcAccess {
    private final String url;
    private final String database;

    public JdbcAccess(String database) {
        this.database = database;
        this.url = "jdbc:mysql://localhost:3306/" + database + "?useSSL=false&serverTimezone=UTC";
    }

    public void execute(String sql) throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    Connection getConnection() throws SQLException {
        loadDriver();
        return DriverManager.getConnection(url, "usuario", "senha"); // Inclua usuário e senha se necessário
    }

    private void loadDriver() throws SQLException {
        try {
            DriverManager.getConnection(url); // Registrando driver implicitamente
        } catch (Exception cause) {
            throw new SQLException(cause.getMessage());
        }
    }

    public String getFirstRowFirstColumn(String query) throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);
            results.next();
            return results.getString(1);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public PreparedStatement prepare(String sql) throws SQLException {
        Connection connection = getConnection();
        return connection.prepareStatement(sql);
    }

    public List<String> getUnique(
            PreparedStatement statement, String... values)
            throws SQLException {
        int i = 1;
        for (String value : values)
            statement.setString(i++, value);
        ResultSet results = statement.executeQuery();
        results.next();
        List<String> row = new ArrayList<>();
        ResultSetMetaData metadata = results.getMetaData();
        for (int column = 1; column <= metadata.getColumnCount(); column++)
            row.add(results.getString(column));
        return row;
    }
}