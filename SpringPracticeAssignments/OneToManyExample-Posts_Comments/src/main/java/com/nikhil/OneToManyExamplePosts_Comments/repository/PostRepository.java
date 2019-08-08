package com.nikhil.OneToManyExamplePosts_Comments.repository;

import com.nikhil.OneToManyExamplePosts_Comments.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>
{
}
