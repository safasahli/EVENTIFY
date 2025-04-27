package com.example.demo;
import com.example.demo.services.MediaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class EventiqueApplication implements CommandLineRunner {

	private final MediaService mediaService;

    public EventiqueApplication(MediaService mediaService) {
        this.mediaService = mediaService;
    }

	@Override
	public void run(String... args) throws Exception {
		mediaService.init();
	}

    public static void main(String[] args) {
		SpringApplication.run(EventiqueApplication.class, args);
	}

}
