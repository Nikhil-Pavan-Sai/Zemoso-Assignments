package com.nikhil.ManyToManyExamplePosts_Tags.repository;

import com.nikhil.ManyToManyExamplePosts_Tags.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
