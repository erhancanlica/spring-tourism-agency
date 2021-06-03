package bmmf.turzimProje.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Bcryt {
    public static void main(String[] args) {
       BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
       String test = encoder.encode("1");
        System.out.println(test);

    }
}
