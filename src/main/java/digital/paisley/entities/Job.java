package digital.paisley.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    public String id;
    public String title;
    public String description;
    public ExperienceLevel experienceLevel;
    public JobType jobType;
    public ProjectLength projectLength;
    public ProjectType projectType;
    public Client client;
}
