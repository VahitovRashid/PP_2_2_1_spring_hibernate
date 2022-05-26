package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add(new User("User1", "Lastname1", "user1@mail.ru",
              new Car(111, "Toyota")));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru",
              new Car(222, "Mazda")));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru",
              new Car(333, "UAZ")));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",
              new Car(444, "BMW")));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car series = "+user.getCar().getSeries());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println();
      }

      User user1 = userService.selectUserByCar(333, "UAZ");
      System.out.println("Id = "+user1.getId());
      System.out.println("First Name = "+user1.getFirstName());
      System.out.println("Last Name = "+user1.getLastName());
      System.out.println("Email = "+user1.getEmail());
      System.out.println("Car series = "+user1.getCar().getSeries());
      System.out.println("Car model = "+user1.getCar().getModel());
      System.out.println();

      context.close();
   }
}
