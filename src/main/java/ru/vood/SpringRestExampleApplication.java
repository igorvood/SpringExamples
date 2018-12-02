package ru.vood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

@SpringBootApplication
/*
@EnableAutoConfiguration
@ComponentScan
*/
public class SpringRestExampleApplication {

/*
    @Bean
     RunLookup runLookup(){
        return new RunLookup();
    }
*/


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringRestExampleApplication.class, args);


/*
        final StudentServices bean = context.getBean(StudentServices.class);
        final StudentServices bean1 = context.getBean(StudentServices.class);
        final StudentServices bean3 = context.getBean(StudentServices.class);
*/
/*
        System.out.println(bean.getNotification());
        System.out.println(bean1.getNotification());
        System.out.println(bean3.getNotification());
*/

/*
        System.out.println(bean3.getNotification().i);
        System.out.println(bean1.getNotification().i);
*/
/*
        System.out.println(bean.getNotification().i);
        System.out.println(bean.getNotification().i);
        System.out.println(bean.getNotification().i);
        System.out.println(bean.getNotification().i);
*/


        final IntStream intStream = IntStream.of(100);
        final int sum = intStream.filter(getIntPredicate()).sum();

    }

    private static IntPredicate getIntPredicate() {
        return value -> value == 100;
    }

    @Bean
    public RestTemplate geRestTemplate() {
        return new RestTemplate();
    }
}
