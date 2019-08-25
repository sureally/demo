package hello;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName hello.SmokeTest
 * @Desciption
 * @Author Shu WJ
 * @DateTime 2019-08-25 21:20
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

  @Autowired
  private HomeController homeController;

  @Test
  public void contextLoads() throws Exception {
    Assertions.assertThat(homeController).isNotNull();
  }
}
