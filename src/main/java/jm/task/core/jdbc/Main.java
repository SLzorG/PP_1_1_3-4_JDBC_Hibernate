package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Kolya", "Popkin", (byte) 52);
        userService.saveUser("Boris", "Britva", (byte) 41);
        userService.saveUser("Tony", "Poolya", (byte) 26);
        userService.saveUser("Margarit", "Staysi", (byte) 17);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
