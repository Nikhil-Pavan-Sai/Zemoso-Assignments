package com.nikhil.ManyToManyExamplePosts_Tags.controller;

import com.nikhil.ManyToManyExamplePosts_Tags.model.Post;
import com.nikhil.ManyToManyExamplePosts_Tags.model.Tag;
import com.nikhil.ManyToManyExamplePosts_Tags.repository.PostRepository;
import com.nikhil.ManyToManyExamplePosts_Tags.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Post_Tag_Controller implements CommandLineRunner {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;


    @Override
    public void run(String... args) throws Exception {

        postRepository.deleteAllInBatch();
        tagRepository.deleteAllInBatch();

        //create a post
        Post post = new Post("Hibernate Many to Many Example with Spring Boot"
                ,"Learn how to map a many to many relationship using hibernate"
                ,"Entire Post content with Sample code");

        Tag tag1 = new Tag("Spring Boot");
        Tag tag2 = new Tag("Hibernate");

        assert post.getTags() != null;
        post.getTags().add(tag1);
        post.getTags().add(tag2);

        assert tag1.getPosts() != null;
        tag1.getPosts().add(post);
        assert tag2.getPosts() != null;
        tag2.getPosts().add(post);

        postRepository.save(post);

    }

}
