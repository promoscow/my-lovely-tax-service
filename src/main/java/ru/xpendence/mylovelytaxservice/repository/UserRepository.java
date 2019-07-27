package ru.xpendence.mylovelytaxservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.xpendence.mylovelytaxservice.entity.User;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 11:50
 * e-mail: v.chernyshov@pflb.ru
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
