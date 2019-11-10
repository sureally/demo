package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName hello.GreetingController
 * @Desciption
 * @Author Shu WJ
 * @DateTime 2019-08-25 21:01
 * @Version 1.0
 **/
@Controller
public class GreetingController {
  private final GreetingService greetingService;

  public GreetingController(GreetingService greetingService) {
    this.greetingService = greetingService;
  }

  @RequestMapping("/greeting")
  public @ResponseBody String greeting() {
    return greetingService.greet();
  }
}
