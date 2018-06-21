package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import com.example.volcano.login.Login;
import com.example.volcano.service.ILoginService;
import java.util.List;

@Controller
@SpringBootApplication(scanBasePackages={"com.example.volcano", "com.example.volcano.service", "com.example.volcano.repository"})
public class DemoApplication {

    @RequestMapping("/")
    @ResponseBody
    String home3() {
      return "Hello, Volcaneers! 🌋";
    }

    @RequestMapping("/HGFirstChange")
    @ResponseBody
    String home() {
      return "Dan is AWESOME!!!! 🌋";
    }

    @RequestMapping("/getMessages")
    @ResponseBody
    String home2() {
      return "Messages: None";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
