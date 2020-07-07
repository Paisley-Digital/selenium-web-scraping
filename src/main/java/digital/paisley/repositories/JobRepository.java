package digital.paisley.repositories;

import digital.paisley.entities.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public  interface   JobRepository extends MongoRepository<Job,String> {

    Job findByTitle(String title);

}
