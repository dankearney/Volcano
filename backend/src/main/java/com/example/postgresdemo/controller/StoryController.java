package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Story;
import com.example.postgresdemo.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class StoryController {

    @Autowired
    private StoryRepository storyRepository;

    @GetMapping("/stories") //mapped to a table named stories in database
    public Page<Story> getStories(Pageable pageable) {
        return storyRepository.findAll(pageable);
    }

    @PostMapping("/stories")
    public Story createStory(@Valid @RequestBody Story story) {
        return storyRepository.save(story);
    }

    @PutMapping("/stories/{storyId}") //get story according to story primary key in TABLE
    public Story updateStory(@PathVariable Long storyId,
                                   @Valid @RequestBody Story storyRequest) {
        return storyRepository.findById(storyId)
                .map(story -> { //all these are passing changes on model back to repository
                    story.setStoryName(storyRequest.getStoryName());
                    story.setType(storyRequest.getType());
                    story.setPriority(storyRequest.getPriority());
                    story.setLabel(storyRequest.getLabel());
                    story.setStatus(storyRequest.getStatus());
                    story.setResolution(storyRequest.getResolution());
                    story.setDescription(storyRequest.getDescription());
                    story.setAttachment(storyRequest.getAttachment());
                    return storyRepository.save(story);
                }).orElseThrow(() -> new ResourceNotFoundException("Story not found with id " + storyId));
    }

    @DeleteMapping("/stories/{storyId}")
    public ResponseEntity<?> deleteStory(@PathVariable Long storyId) {
        return storyRepository.findById(storyId)
                .map(story -> {
                    storyRepository.delete(story);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Story not found with id " + storyId));
    }
}
