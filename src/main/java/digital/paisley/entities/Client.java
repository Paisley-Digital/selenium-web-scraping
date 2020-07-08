package digital.paisley.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    public String id;
    public boolean paymentVerified;
    public String location;
    public String totalSpent;
    public int jobsPosted;
    public int hireRate;
    public int openJobs;
    public Date contractDate;

}
