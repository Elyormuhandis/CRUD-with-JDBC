import java.sql.*;
import java.time.LocalDate;

public class DatabaseService {
    private String url = "jdbc:postgresql://localhost:5432/jdbc";
    private String dbUser = "postgres";
    private String dbPassword = "123";

    public void addUser(User user) {
        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
//               Statement statement = connection.createStatement();)
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(firstName, lastName, username, password) values(?, ?, ?, ?);"))
        {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
//            String query = "INSERT INTO users (firstName, lastName, username, password) values ('" + user.getFirstName() +
//                    "','" + user.getLastName() + "','" + user.getUsername() + "','" + user.getPassword() + "');";
//            statement.execute(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUsers() {

        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
             Statement statement = connection.createStatement();) {
            String query = "SELECT * FROM users;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("user_id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
                System.out.println(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUser(User user) {

        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
             Statement statement = connection.createStatement()) {

            String query = "UPDATE users SET ";
            if (!user.getFirstName().isEmpty())
                query += "firstName='" + user.getFirstName() + "', ";
            if (!user.getLastName().isEmpty())
                query += "lastName='" + user.getLastName() + "', ";
            if (!user.getUsername().isEmpty())
                query += "username='" + user.getUsername() + "', ";
            if (!user.getPassword().isEmpty())
                query += "password='" + user.getPassword() + "' ";
            if (!query.equals("UPDATE users SET ")) {
                if (query.endsWith(", "))
                    query = query.substring(0, query.length() - 2);
            }
            query += " WHERE user_id=" + user.getUserId() + ";";
            statement.execute(query);
            System.out.println("User " + user.getUsername() + " has been successfully edited");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {

        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
             Statement statement = connection.createStatement()){

            String query = "DELETE FROM users WHERE user_id=" + id + ";";
            statement.execute(query);
            System.out.println("User with id=" + id + " is successfully deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
