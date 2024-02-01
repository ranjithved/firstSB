package com.example.firstSB.Controller;

import com.example.firstSB.model.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
        public class HelloController {
        @GetMapping("/greet")
            public String sayHello() {
                 return "Welcome to SpringBoot";
    }

        @PostMapping("/post")
    public String postMessage(@RequestBody Message message){
            System.out.println("Message Id: "+message.getMsgid());
            System.out.println("Message: "+message.getMsg());
            return "Your Input is Accepted";
        }
}
