package main.uangku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import main.uangku.models.entities.Income;

public interface ExpanseRepository extends JpaRepository<Income, Long> {
    @Query("SELECT SUM(i.value) FROM Income i WHERE i.category = :category")
    int sumExpanse(@Param("category") String category);

    @Query("SELECT i FROM Income i WHERE i.category = :category ORDER BY i.date DESC")
    List<Income> getAllExpanseByDate(@Param("category") String category);
}