package com.example.productservicenaman.Controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
        // get/hello/say at that time below method will respond.
    @GetMapping("/say/{name}/{count}")
    public String sayHello(@PathVariable("name") String name,@PathVariable("count")int count)
    {
        String ans = "";
        for(int i=0; i<count; i++){
            ans += "Hello" + name;
            ans+="<br>";
        }
        return ans;
    }
}
