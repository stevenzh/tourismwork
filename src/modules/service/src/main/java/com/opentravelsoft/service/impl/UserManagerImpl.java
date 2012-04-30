package com.opentravelsoft.service.impl;

import java.util.List;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.opentravelsoft.model.Member;
import com.opentravelsoft.providers.MemberDao;
import com.opentravelsoft.service.UserExistsException;
import com.opentravelsoft.service.UserManager;
import com.opentravelsoft.service.UserService;

/**
 * Implementation of UserManager interface.
 * 
 */
@Service("userManager")
@WebService(serviceName = "UserService", endpointInterface = "com.opentravelsoft.service.UserService")
public class UserManagerImpl extends GenericManagerImpl<Member, Long> implements
    UserManager, UserService {
  private PasswordEncoder passwordEncoder;

  private MemberDao userDao;

  // @Autowired
  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Autowired
  public void setUserDao(MemberDao userDao) {
    this.dao = userDao;
    this.userDao = userDao;
  }

  /**
   * {@inheritDoc}
   */
  public Member getUser(String userId) {
    return userDao.get(new Long(userId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Member> getUsers() {
    return userDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Member saveUser(Member user) throws UserExistsException {

    if (user.getVersion() == null) {
      // if new user, lowercase userId
      user.setUsername(user.getUsername().toLowerCase());
    }

    // Get and prepare password management-related artifacts
    boolean passwordChanged = false;
    if (passwordEncoder != null) {
      // Check whether we have to encrypt (or re-encrypt) the password
      if (user.getVersion() == null) {
        // New user, always encrypt
        passwordChanged = true;
      } else {
        // Existing user, check password in DB
        String currentPassword = userDao.getUserPassword(user.getUsername());
        if (currentPassword == null) {
          passwordChanged = true;
        } else {
          if (!currentPassword.equals(user.getPassword())) {
            passwordChanged = true;
          }
        }
      }

      // If password was changed (or new user), encrypt it
      if (passwordChanged) {
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(),
            null));
      }
    } else {
      log.warn("PasswordEncoder not set, skipping password encryption...");
    }

    try {
      return userDao.saveUser(user);
    } catch (DataIntegrityViolationException e) {
      // e.printStackTrace();
      log.warn(e.getMessage());
      throw new UserExistsException("User '" + user.getUsername()
          + "' already exists!");
    } catch (JpaSystemException e) { // needed for JPA
                                     // e.printStackTrace();
      log.warn(e.getMessage());
      throw new UserExistsException("User '" + user.getUsername()
          + "' already exists!");
    }
  }

  /**
   * {@inheritDoc}
   */
  public void removeUser(String userId) {
    log.debug("removing user: " + userId);
    userDao.remove(new Long(userId));
  }

  /**
   * {@inheritDoc}
   * 
   * @param username the login name of the human
   * @return User the populated user object
   * @throws UsernameNotFoundException thrown when username not found
   */
  public Member getUserByUsername(String username)
      throws UsernameNotFoundException {
    return (Member) userDao.loadUserByUsername(username);
  }
}
