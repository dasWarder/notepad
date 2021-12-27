package com.example.notepad.service.tag;

import com.example.notepad.dao.TagRepository;
import com.example.notepad.model.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

  private final TagRepository tagRepository;

  @Override
  public Tag saveTag(Tag tag) {

    log.info("In TagServiceImpl.saveTag - Store a new tag");

    return tagRepository.save(tag);
  }

  @Override
  public Tag updateTag(Long id, Tag tag) {

    log.info("In TagServiceImpl.updateTag - Update a tag by id = {}", id);
    tag.setId(id);

    return tagRepository.save(tag);
  }

  @Override
  public Page<Tag> getTags(Pageable pageable) {

    log.info("In TagServiceImpl.getTags - get tag pages");

    return tagRepository.findAll(pageable);
  }

}
