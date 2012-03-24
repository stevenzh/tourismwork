package com.opentravelsoft.service;

import java.util.List;
import javax.jws.WebService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.opentravelsoft.model.Member;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@WebService
public interface UserService {
    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Member getUser(String userId);

    /**
     * Finds a user by their username.
     * @param username the user's username used to login
     * @return User a populated user object
     * @throws org.springframework.security.userdetails.UsernameNotFoundException
     *         exception thrown when user not found
     */
    Member getUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Member> getUsers();

    /**
     * Saves a user's information
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return updated user
     */
    Member saveUser(Member user) throws UserExistsException;

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeUser(String userId);
}
