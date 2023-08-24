package com.booking.user;

public class UserDAO {
    private static final User[] users;
    static {
        users = new User[]{
                new User("Hamza"),
                new User("Nissrine")
        };
    }
    public User[] getUsers(){
        return users;
    }

}
