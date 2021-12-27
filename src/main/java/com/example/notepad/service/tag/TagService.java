package com.example.notepad.service.tag;

import com.example.notepad.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {

    Tag saveTag(Tag tag);

    Tag updateTag(Long id, Tag tag);

    Page<Tag> getTags(Pageable pageable);
}
