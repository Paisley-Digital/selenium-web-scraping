package digital.paisley;

import digital.paisley.entities.Job;
import digital.paisley.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private JobRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new Job("Job1", "Java"));
        repository.save(new Job("Job2", "Spring Boot"));

        System.out.println("Jobs found with findAll():");
        System.out.println("-------------------------------");
        for (Job job : repository.findAll()) {
            System.out.println(job);
        }
        System.out.println();

        System.out.println("Job found with findByTitle('Java'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByTitle("Java"));

    }
}
