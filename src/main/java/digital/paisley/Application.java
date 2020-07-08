package digital.paisley;

import digital.paisley.entities.Client;
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

        repository.save(Job.builder().id("Job1").title("Java").client(Client.builder().id("Client1").jobsPosted(1).build()).build());
        repository.save(Job.builder().id("Job2").title("Spring Boot").client(Client.builder().id("Client2").jobsPosted(2).build()).build());

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
