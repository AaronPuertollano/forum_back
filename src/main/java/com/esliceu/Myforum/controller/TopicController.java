package com.esliceu.Myforum.controller;


import com.esliceu.Myforum.dto.*;
import com.esliceu.Myforum.model.Category;
import com.esliceu.Myforum.model.Reply;
import com.esliceu.Myforum.model.Topic;
import com.esliceu.Myforum.service.ReplyService;
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

    @Autowired
    ReplyService replyService;

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

    @PostMapping("/{id}/replies")
    public ResponseEntity<ReplyDTO> createReply(@PathVariable Long id, @RequestBody CreateReplyDTO request,
            HttpServletRequest httpRequest) {

        String email = (String) httpRequest.getAttribute("email");

        Reply reply = replyService.createReply(id, request, email);

        return ResponseEntity.ok(new ReplyDTO(reply));
    }

    @DeleteMapping("/{topicId}/replies/{replyId}")
    public ResponseEntity<Boolean> deleteReply(
            @PathVariable Long topicId,
            @PathVariable Long replyId,
            HttpServletRequest httpRequest) {

        String email = (String) httpRequest.getAttribute("email");

        boolean deleted = replyService.deleteReply(topicId, replyId, email);

        return ResponseEntity.ok(deleted);
    }

    @PutMapping("/{topicId}/replies/{replyId}")
    public ResponseEntity<ReplyDTO> updateReply(
            @PathVariable Long topicId,
            @PathVariable Long replyId,
            @RequestBody UpdateReplyDTO request,
            HttpServletRequest httpRequest) {

        Reply updatedReply = replyService.updateReply(topicId, replyId, request);

        return ResponseEntity.ok(new ReplyDTO(updatedReply));
    }

}
