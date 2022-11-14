package utils;

import entity.User_InmemoryStorage;

public class ObjectValidation {
    public static boolean validate(User_InmemoryStorage userSingleton) {

        if (userSingleton.getFirstname() == null || userSingleton.getLastname() == null || userSingleton.getBirthday() == null || userSingleton.getEmail() == null)
        return false;
        return true;
    }
}
