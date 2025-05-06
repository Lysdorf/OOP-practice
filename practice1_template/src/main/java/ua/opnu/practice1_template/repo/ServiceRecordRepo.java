package ua.opnu.practice1_template.repo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.opnu.practice1_template.entity.CarEntity;
import ua.opnu.practice1_template.entity.ServiceRecordEntity;
import ua.opnu.practice1_template.entity.ServiceTypeEntity;

@Repository
public interface ServiceRecordRepo extends JpaRepository<ServiceRecordEntity, Long>{
    // 21. Отримати загальну суму обслуговування автомобіля
    @Query("""
        SELECT COALESCE(SUM(st.standardPrice), 0)
        FROM ServiceRecordEntity sr
        JOIN sr.serviceTypes st
        WHERE sr.car.id = :carId
        """)
    BigDecimal findTotalServiceCostByCarId(@Param("carId") Long carId);

    // 22. Отримати статистику роботи майстра
    List<ServiceRecordEntity> findAllByMechanicId(@Param("mechanicId") Long mechanicId);

    // 23. Отримати найпопулярніші послуги
    @Query("""
        SELECT sr.serviceTypes
        FROM ServiceRecordEntity sr
        GROUP BY sr.serviceTypes
        ORDER BY COUNT(sr) DESC
        """)
    List<ServiceTypeEntity> findMostPopularServiceTypes(Pageable pageable);

    // 24. Отримати обслуговування за період
    List<ServiceRecordEntity> findByDateBetween(LocalDate start, LocalDate end);

    // 25. Отримати автомобілі з найчастішим обслуговуванням
     @Query("""
        SELECT sr.car
        FROM ServiceRecordEntity sr
        GROUP BY sr.car
        ORDER BY COUNT(sr) DESC
        """)
    List<CarEntity> findCarsByServiceFrequency(Pageable pageable);
}





 