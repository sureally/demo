package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ClassName hello.WebMockTest
 * @Desciption
 * @Author Shu WJ
 * @DateTime 2019-08-25 21:46
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class WebMockTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GreetingService greetingService;

  @Test
  public void greetingShouldReturnMessageFromService() throws Exception {
    when(greetingService.greet()).thenReturn("Hello Mock");
    this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
      .andExpect(content().string(containsString("Hello Mock")));
  }
}
