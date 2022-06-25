package info.dallog.common;

import org.springframework.web.client.RestTemplate;

public class SingleTonRestTemplate {
    private static RestTemplate restTemplate;

    private SingleTonRestTemplate(){};

    public static RestTemplate getInstance(){
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final RestTemplate INSTANCE = new RestTemplate();
    }
}
