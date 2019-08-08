package com.nikhil.ManyToManyExamplePosts_Tags.main_class;

import com.nikhil.ManyToManyExamplePosts_Tags.model.Post;
import com.nikhil.ManyToManyExamplePosts_Tags.model.Tag;
import com.nikhil.ManyToManyExamplePosts_Tags.repository.PostRepository;
import com.nikhil.ManyToManyExamplePosts_Tags.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManyToManyExamplePostsTagsApplication
{

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyExamplePostsTagsApplication.class, args);
	}

}
