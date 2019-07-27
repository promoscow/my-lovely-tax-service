package ru.xpendence.mylovelytaxservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.xpendence.mylovelytaxservice.entity.Inspection;

import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 15:20
 * e-mail: v.chernyshov@pflb.ru
 */
@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {

    @Query(
            value = "select * from inspections as i where i.inn = :inn",
            nativeQuery = true
    )
    List<Inspection> findByUserInn(@Param("inn") Long inn);
}
