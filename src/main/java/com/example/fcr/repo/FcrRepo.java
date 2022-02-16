package com.example.fcr.repo;


import com.example.fcr.model.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FcrRepo extends JpaRepository<Answers, String> {

    @Query(value = "select fa.case " +
            "from public.fcr_allanswers fa " +
            "where fa.created_at between :start and :end",
            nativeQuery = true)
    List<String> getAll(@Param("start")LocalDate start,
                                @Param("end")LocalDate end);

    @Query(value = "select ffa.case " +
            "from public.fcr_final_answers ffa " +
            "where ffa.created_at between :start and :end",
            nativeQuery = true)
    List<String> getResult(@Param("start")LocalDate start,
                        @Param("end")LocalDate end);
}
