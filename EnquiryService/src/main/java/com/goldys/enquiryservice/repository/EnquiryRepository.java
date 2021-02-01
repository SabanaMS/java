package com.goldys.enquiryservice.repository;

import com.goldys.enquiryservice.model.Enquiry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
 * This class is implementing the MongoRepository interface for Enquiry.
 * Annotate this class with @Repository annotation
 */
@Repository
public interface EnquiryRepository extends MongoRepository<Enquiry, String> {

}
