package digital.paisley.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.asynchttpclient.uri.Uri;
import org.springframework.data.annotation.Id;

import java.net.URL;

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
    public String jobLink;
    public Client client;
}
