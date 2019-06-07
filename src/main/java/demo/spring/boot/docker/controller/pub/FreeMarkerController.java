package demo.spring.boot.docker.controller.pub;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class FreeMarkerController {


    @GetMapping(value = "/free-marker")
    public String freeMarker(Map<String, Object> map) {
        map.put("name", "王海潮");
        map.put("age", "25");
        return "freeMarker";
    }
}
