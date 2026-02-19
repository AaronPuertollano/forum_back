package com.esliceu.Myforum.controller;


import com.esliceu.Myforum.dto.*;
import com.esliceu.Myforum.model.Category;
import com.esliceu.Myforum.model.Topic;
import com.esliceu.Myforum.service.TopicService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicResponseDTO> createTopic(
            @RequestBody CreateTopicDTO request,
            HttpServletRequest httpRequest) {

        String email = (String) httpRequest.getAttribute("email");

        Topic topic = topicService.createTopic(request, email);

        return ResponseEntity.ok(new TopicResponseDTO(topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailDTO> getTopicById(@PathVariable Long id) {

        Topic topic = topicService.getTopicById(id);

        return ResponseEntity.ok(new TopicDetailDTO(topic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTopic(@PathVariable Long id) {

        boolean deleted = topicService.deleteTopic(id);
        return ResponseEntity.ok(deleted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDetailDTO> updateTopic(
            @PathVariable Long id,
            @RequestBody UpdateTopicDTO request,
            HttpServletRequest httpRequest) {

        String email = (String) httpRequest.getAttribute("email");

        Topic topic = topicService.updateTopic(id, request, email);

        return ResponseEntity.ok(new TopicDetailDTO(topic));
    }



}
