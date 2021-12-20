package com.example.notepad.service;

import com.example.notepad.model.Tag;
import com.example.notepad.service.exception.TagNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {

    Tag saveTag(Tag tag);

    Tag updateTag(Long id, Tag tag);

    Tag getTagById(Long id) throws TagNotFoundException;

    Tag getTabByTagName(String tagName) throws TagNotFoundException;

    void deleteTagById(Long id);

    void deleteTagByTagName(String tagName);

    Page<Tag> getTags(Pageable pageable);
}
