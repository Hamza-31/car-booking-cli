package com.booking.user;

import java.util.List;
import java.util.UUID;

public class UserService {
    private UserFileDataAccessService userDAO = new UserFileDataAccessService();

    public List<User> getUsers(){

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
