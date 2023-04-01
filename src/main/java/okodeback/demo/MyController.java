package okodeback.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private RestTemplate restTemplate;
    private String jsonResponse = "";
    private String apiURL = "https://api.themoviedb.org/3/trending/movie/day?api_key=7b2837e0d92be9a84e4b12ee4c2c17a9";
    private String apiURLShort = "https://api.themoviedb.org/3/movie/";
    private String apiKey = "?api_key=7b2837e0d92be9a84e4b12ee4c2c17a9";
    private String language = "&language=en-US";

    @GetMapping("/movies")
    public String getMovies() {
        // make a GET request to another API and retrieve JSON response
        if (this.jsonResponse.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<String> response = restTemplate.exchange(this.apiURL, HttpMethod.GET, entity, String.class);
            this.jsonResponse = response.getBody();
        }
            
        // return the JSON response
        return this.jsonResponse;
    }

    @GetMapping("/movie/{id}")
    public String getMovie(@PathVariable("id") String id) {
        // store the parameter value and use it to make a call to another API
        String url = apiURLShort + id + apiKey + language;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String jsonRes = response.getBody();

        // return the JSON response
        return jsonRes;
    }

}

