package ru.xpendence.mylovelytaxservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.xpendence.mylovelytaxservice.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 11:50
 * e-mail: v.chernyshov@pflb.ru
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "select u.email from users as u where u.inn in " +
                    "(select i.inn from inspections as i where i.start_date > start and i.start_date < finish)",
            nativeQuery = true
    )
    List<String> getEmailsForInform(@Param("start") LocalDate start, @Param("finish") LocalDate finish);

    Optional<User> getByUsernameAndPassword(String username, String password);
}
