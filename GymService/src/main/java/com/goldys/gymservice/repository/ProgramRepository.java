package com.goldys.gymservice.repository;

import com.goldys.gymservice.model.Program;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/*
 * This class is implementing the MongoRepository interface for Program.
 * Annotate this class with @Repository annotation
 * */
public interface ProgramRepository extends MongoRepository<Program, String> {

    List<Program> findByIsActiveTrue();

    List<Program> findByDurationInMonths(int durationInMonths);
}
