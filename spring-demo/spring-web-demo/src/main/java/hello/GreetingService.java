package hello;

import org.springframework.stereotype.Service;

/**
 * @ClassName hello.GreetingService
 * @Desciption
 * @Author Shu WJ
 * @DateTime 2019-08-25 21:01
 * @Version 1.0
 **/
@Service
public class GreetingService {
  public String greet() {
    return "Hello world";
  }
}
