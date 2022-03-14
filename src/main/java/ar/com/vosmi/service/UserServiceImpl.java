package ar.com.vosmi.service;

import ar.com.vosmi.domain.User;
import ar.com.vosmi.repository.UserRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class UserServiceImpl implements UserService{

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(User user) {
        entityManager.merge(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void updateUser(User user) {
        User myUser = userRepository.findById(user.getId());
        myUser.setEmail(user.getEmail());
        myUser.setLogin(user.getLogin());
        myUser.setLastName(user.getLastName());
        myUser.setFirstName(user.getFirstName());
        myUser.setDocumentNumber(user.getDocumentNumber());
        userRepository.save(myUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User verifiedCredentialsAndGetUser(User user){
        String query = "FROM User WHERE email = :email";
        List<User> listUser = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();

        if(listUser.isEmpty()){
            return null;
        }
        String passwordHashed = listUser.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHashed, user.getPassword())){
            return listUser.get(0);
        }
        return null;
    }
}
