package info.dallog.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainController {

    @GetMapping
    public String main(){
        return "index";
    }

    @GetMapping("/elements")
    public String elements(){
        return "elements";
    }
}
