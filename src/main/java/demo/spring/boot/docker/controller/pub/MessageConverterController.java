package demo.spring.boot.docker.controller.pub;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageConverterController {

    @GetMapping(value="/converter")
    public String converter(){

        return "哈哈";
    }
}
