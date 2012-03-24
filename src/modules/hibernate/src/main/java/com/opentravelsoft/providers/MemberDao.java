package com.opentravelsoft.providers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opentravelsoft.model.Member;

import java.util.List;

/**
 * User Data Access Object (GenericDao) interface.
 * 
 */
public interface MemberDao extends GenericDao<Member, Long>
{

    /**
     * Gets users information based on login name.
     * 
     * @param username the user's username
     * @return userDetails populated userDetails object
     * @throws org.springframework.security.userdetails.UsernameNotFoundException
     *             thrown when user not found in database
     */
    @Transactional
    UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException;

    /**
     * Gets a list of users ordered by the uppercase version of their username.
     * 
     * @return List populated list of users
     */
    List<Member> getUsers();

    /**
     * Saves a user's information.
     * 
     * @param user the object to be saved
     * @return the persisted User object
     */
    Member saveUser(Member user);

    /**
     * Retrieves the password in DB for a user
     * 
     * @param username the user's username
     * @return the password in DB, if the user is already persisted
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    String getUserPassword(String username);

    // ---------------------------------------------------------------
    public int register(Member customer);

    public Member getMemberById(long userId);

    public Member getMemberByName(String userName);

    public int update(Member customer);

    public String validUid(String uid);

    public String getMemberPwd(String mobileNo);

    public int modifyPassword(Member customer);

}
