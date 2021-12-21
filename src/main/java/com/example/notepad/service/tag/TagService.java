package com.example.notepad.service.tag;

import com.example.notepad.model.Tag;
import com.example.notepad.service.exception.TagNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface TagService {

    Tag saveTag(Tag tag);

    Tag updateTag(Long id, Tag tag);

    Tag getTagById(Long id) throws TagNotFoundException;

    Tag getTagByTagName(String tagName) throws TagNotFoundException;

    void deleteTagById(Long id);

    void deleteTagByTagName(String tagName);

    Page<Tag> getTags(Pageable pageable);
}
