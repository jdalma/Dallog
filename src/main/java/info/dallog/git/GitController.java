package info.dallog.git;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/git")
public class GitController {

    @GetMapping()
    public String git(RedirectAttributes redirect){
        redirect.addAttribute("client_id" , GIT_INFO.CLIENT_ID);
        redirect.addAttribute("redirect_uri" , GIT_INFO.REDIRECT_URL);
        redirect.addAttribute("login" , GIT_INFO.ID);
        return "redirect:https://github.com/login/oauth/authorize";
    }

    @GetMapping("/afterLogin")
    public String afterLogin(@RequestParam String code){
        if(Objects.isNull(code)){
            throw new IllegalArgumentException("Github 로그인에 실패했습니다.");
        }
        log.info("afterLogin code = {}" , code);

        RestTemplate restTemplate = SingleTonRestTemplate.getInstance();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id" , GIT_INFO.CLIENT_ID);
        map.add("client_secret" , GIT_INFO.SECRET);
        map.add("code" , code);

        ResponseEntity<GitDTO> response = restTemplate.postForEntity("https://github.com/login/oauth/access_token" , map , GitDTO.class);
        log.info("responseEntity = {}" , response.getBody());

        addIssue(Objects.requireNonNull(response.getBody()));

        return "redirect:https://github.com/jdalma/dev-study/issues";
    }

    public void addIssue(GitDTO dto){
        RestTemplate restTemplate = SingleTonRestTemplate.getInstance();
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept" , "application/vnd.github.v3+json");
        headers.set("Authorization" , "token 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");
//        headers.set("Authorization" , "token " + dto.getAccess_token());

        Map<String, String> map = new HashMap<>();
        map.put("title" , LocalDateTime.now() + " API ISSUE TEST");
        map.put("body" , LocalDateTime.now() + " 테스트");

        HttpEntity request = new HttpEntity(map , headers);

        ResponseEntity<String> response = restTemplate.postForEntity("https://api.github.com/repos/jdalma/dev-study/issues" , request , String.class);

        log.info("response = {}" , response.getBody());

    }
}
