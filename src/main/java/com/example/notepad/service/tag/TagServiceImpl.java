package com.example.notepad.service.tag;

import com.example.notepad.dao.TagRepository;
import com.example.notepad.model.Tag;
import com.example.notepad.service.exception.TagNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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
  public Tag getTagById(Long id) throws TagNotFoundException {

    log.info("In TagServiceImpl.getTagById - Get tag by id = {}", id);

    return tagRepository
        .findById(id)
        .orElseThrow(
            () -> new TagNotFoundException(String.format("Tag with id = %d not found", id)));
  }

  @Override
  public Tag getTagByTagName(String tagName) throws TagNotFoundException {

    log.info("In TagServiceImpl.getTagByTagName - Get tag by tagName = {}", tagName);

    return tagRepository
            .getTagByTagName(tagName)
            .orElseThrow(
                    () -> new TagNotFoundException(String.format("Tag with tagName = %s not found", tagName)));
  }

  @Override
  public void deleteTagById(Long id) {

    log.info("In TagServiceImpl.deleteTagById - Delete tag by id = {}", id);

    tagRepository.deleteById(id);
  }

  @Override
  public void deleteTagByTagName(String tagName) {

    log.info("In TagServiceImpl.deleteTagByTagName - Delete tag by tagName = {}", tagName);

    tagRepository.deleteTagByTagName(tagName);
  }

  @Override
  public Page<Tag> getTags(Pageable pageable) {

    log.info("In TagServiceImpl.getTags - get tag pages");

    return tagRepository.findAll(pageable);
  }

}
