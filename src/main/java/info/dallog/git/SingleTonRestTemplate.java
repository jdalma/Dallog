package info.dallog.git;

import org.springframework.web.client.RestTemplate;

public class SingleTonRestTemplate {
    private static RestTemplate restTemplate;

    private SingleTonRestTemplate(){};

    public static synchronized RestTemplate getInstance(){
        if(restTemplate == null){
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }
}
