package com.example.lasthm050822;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyController {
    private SystemProfile profile;

    public MyController(SystemProfile profile){
        this.profile = profile;
    }

    @GetMapping("profile")
    String doProfile(){
      return  profile.getProfile();
    }
}
