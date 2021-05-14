package me.ferrous;

import me.ferrous.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.stream.IntStream;

@Configuration("dev")
public class DevelopmentConfiguration {

    @Bean
    public CommandLineRunner dataLoader(BoardRepository boardRepository) {
        return args -> {
            IntStream.rangeClosed(0, 10)
                    .forEach(value -> {
                        boardRepository.save(Board.builder()
                                .content("Content" + value)
                                .title("Title" + value)
                                .creationDate(new Date())
                                .updateDate(new Date())
                                .build());
                    });
        };
    }

}
