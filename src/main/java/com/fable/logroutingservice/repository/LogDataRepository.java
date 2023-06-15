package com.fable.logroutingservice.repository;

import com.fable.logroutingservice.model.LogData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDataRepository extends JpaRepository<LogData,Integer> {
}
