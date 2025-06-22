package com.example.movie_booking.repository;

import com.example.movie_booking.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovieId(Long movieId);
    List<Show> findByScreenId(Long screenId);
    List<Show> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);


    @Query("""
             SELECT s FROM Show s
            WHERE s.screen.id = :screenId
            AND (
                    (:startTime BETWEEN s.startTime AND s.endTime)
    OR (:endTime BETWEEN s.startTime AND s.endTime)
    OR (s.startTime BETWEEN :startTime AND :endTime)
    )""")
    List<Show> findOverLappingShows(@Param("screenId") Long screenId, @Param("startTime") LocalDateTime startTime , @Param("endTime") LocalDateTime endTime);

}
