package cool.mawei.web;

import cool.mawei.service.IHelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-03-15 15:16
 */
@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private IHelloWorldService helloWorldServiceImpl;

    @PostMapping("/sayHello")
    @ResponseBody
    public String sayHello() {
        return helloWorldServiceImpl.sayHello();
    }
}
