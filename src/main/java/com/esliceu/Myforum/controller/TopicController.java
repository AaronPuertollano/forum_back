package com.esliceu.Myforum.controller;


import com.esliceu.Myforum.dto.CreateTopicDTO;
import com.esliceu.Myforum.dto.TopicResponseDTO;
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
    public ResponseEntity<TopicResponseDTO> getTopicById(@PathVariable Long id) {

        Topic topic = topicService.getTopicById(id);

        return ResponseEntity.ok(new TopicResponseDTO(topic));
    }

}
