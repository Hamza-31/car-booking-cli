package com.booking.user;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class UserFileDataAccessService implements UserDao{
    @Override
    public User[] getUsers() {
        String path = "src/com/booking/users.csv";
        File file = new File(path);
        User[] users = new User[2];
        try {
            int index = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().split(",");
                users[index] = new User(UUID.fromString(split[0]), split[1]);
                index++;
            }
            return users;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
