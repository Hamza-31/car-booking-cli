package com.booking.user;

import java.util.UUID;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    public User[] getUsers(){
        return userDAO.getUsers();
    }
    public User getUserById(UUID id){
        for (User user : getUsers()) {
            if(id.equals(user.getId())){
                return user;
            }
        }
        return null;
    }

}
