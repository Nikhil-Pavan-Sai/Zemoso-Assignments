package com.nikhil.OneToManyExamplePosts_Comments.main_class;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OneToManyExamplePostsCommentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneToManyExamplePostsCommentsApplication.class, args);
	}

}
