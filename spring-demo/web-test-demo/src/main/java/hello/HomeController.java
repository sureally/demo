package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName hello.HomeController
 * @Desciption
 * @Author Shu WJ
 * @DateTime 2019-08-25 21:01
 * @Version 1.0
 **/
@Controller
public class HomeController {

  @RequestMapping("/")
  public @ResponseBody String greeting() {
    return "Hello world!";
  }
}
