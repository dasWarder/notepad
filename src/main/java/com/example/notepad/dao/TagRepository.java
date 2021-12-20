package com.example.notepad.dao;

import com.example.notepad.model.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {

    Optional<Tag> getTagByTagName(String tagName);

    void deleteTagByTagName(String tagName);
}
