package ar.com.vosmi.repository;
import ar.com.vosmi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Override
    List<User> findAll();

    void deleteById(Long id);

    User findById(Long id);
}