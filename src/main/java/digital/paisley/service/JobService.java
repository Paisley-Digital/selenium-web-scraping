package digital.paisley.service;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import digital.paisley.entities.Job;
import digital.paisley.repositories.JobRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JobService {

    private final JobRepository repository;

    public JobService(JobRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRate = 10000)
    public void findAndSaveNewJob() throws IOException, FeedException {
        List<Job> jobs = new ArrayList<>();

        URL feedSource = new URL("https://www.upwork.com/ab/feed/jobs/atom?category2_uid=531770282580668418&verified_payment_only=1&q=%22java%22+and+%22boot%22&sort=recency&paging=0%3B10&api_params=1&securityToken=df13401ba0696b78e0fb690d4ba18473bd7f8f32fcfe24e7a388520ecb2460e267bac4732d31c7aef1c08c66e7cab5e6d41d476c2f641b7fd9d5fbd6b45a76b1&userUid=1171683308481347584&orgUid=1206521302968496128");
        SyndFeedInput input = new SyndFeedInput();

        HttpURLConnection httpcon = (HttpURLConnection) feedSource.openConnection();
        String encoding = Base64.getEncoder().encodeToString("username:password".getBytes());
        httpcon.setRequestProperty("Authorization", "Basic " + encoding);

        SyndFeed feed = input.build(new XmlReader(feedSource));

        List entries = feed.getEntries();

        entries.stream().forEach(entry -> {
            String link = ((SyndEntryImpl) entry).getLink();
            String linkId = findLinkId(link);
            if (linkId == "") {
                linkId = UUID.randomUUID().toString();
            }
            jobs.add(Job.builder()
                    .description(((SyndEntryImpl) entry).getDescription().getValue())
                    .id(linkId)
                    .title(((SyndEntryImpl) entry).getTitle())
                    .jobLink(link)
                    .build());
            ((SyndEntryImpl) entry).getDescription().getValue();
        });


        repository.deleteAll();

        repository.saveAll(jobs);
        // repository.save(Job.builder().id("Job1").title("Java").client(Client.builder().id("Client1").jobsPosted(1).build()).build());
        //repository.save(Job.builder().id("Job2").title("Spring Boot").client(Client.builder().id("Client2").jobsPosted(2).build()).build());

        System.out.println("Jobs found with findAll():");
        System.out.println("-------------------------------");
        for (Job job : repository.findAll()) {
            System.out.println(job.id);
        }
        System.out.println();

//        System.out.println("Job found with findByTitle('Java'):");
//        System.out.println("--------------------------------");
//        System.out.println(repository.findByTitle("Java").id);

    }


    public String findLinkId(String link) {
        Pattern pattern = Pattern.compile("_%7E(.*?)\\?source=", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(link);
        while (matcher.find()) {
            System.out.println("###################");
            System.out.println(matcher.group(1));
            System.out.println("###################");
            return matcher.group(1);
        }
        return "";
    }

}
