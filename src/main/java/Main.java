import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    User user= new User();
    DatabaseService databaseService = new DatabaseService();
        int i = -1;
        while (i!=0){
        System.out.println("0=>Chiqish, 1=>Add user, 2=>Edit user, 3=>Delete user, 4=>Show all users");
        i = scanner.nextInt();
            switch (i){
                case 1:
                    scanner=new Scanner(System.in);
                    System.out.print("firstName=");
                    user.setFirstName(scanner.nextLine());
                    System.out.print("lastName=");
                    user.setLastName(scanner.nextLine());
                    System.out.print("username=");
                    user.setUsername(scanner.nextLine());
                    System.out.print("password=");
                    user.setPassword(scanner.nextLine());
                    databaseService.addUser(user);
                    System.out.println("User successfully added");
                break;
                case 2:
                    scanner = new Scanner(System.in);
                    System.out.print("Enter userId for edite \n UserID=");
                    user.setUserId(scanner.nextInt());
                    scanner = new Scanner(System.in);
                    System.out.print("FirstName=");
                    user.setFirstName(scanner.nextLine());
                    System.out.print("LastName=");
                    user.setLastName(scanner.nextLine());
                    System.out.print("Username=");
                    user.setUsername(scanner.nextLine());
                    System.out.print("Password=");
                    user.setPassword(scanner.nextLine());
                    databaseService.editUser(user);
                break;
                case 3:
                    scanner = new Scanner(System.in);
                    System.out.print("Enter userId for delete: \n UserID=");
                    databaseService.deleteUser(scanner.nextInt());
                break;
                case 4: databaseService.getUsers();
                break;

            }
        }
    }
}
