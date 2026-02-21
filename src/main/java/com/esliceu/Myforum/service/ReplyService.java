package com.esliceu.Myforum.service;

import com.esliceu.Myforum.dto.CreateReplyDTO;
import com.esliceu.Myforum.dto.UpdateReplyDTO;
import com.esliceu.Myforum.model.Reply;
import com.esliceu.Myforum.model.Topic;
import com.esliceu.Myforum.model.User;
import com.esliceu.Myforum.repo.ReplyRepository;
import com.esliceu.Myforum.repo.TopicRepository;
import com.esliceu.Myforum.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    public Reply createReply(Long topicId, CreateReplyDTO request, String email) {

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found")
                );

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
                );

        Reply reply = new Reply();
        reply.setContent(request.getContent());
        reply.setTopic(topic);
        reply.setUser(user);

        Reply savedReply = replyRepository.save(reply);

        topic.getReplies().add(savedReply);

        return savedReply;
    }

    public boolean deleteReply(Long topicId, Long replyId, String email) {

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Reply not found")
                );

        if (!reply.getTopic().getId().equals(topicId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reply does not belong to topic");
        }

        replyRepository.delete(reply);

        return true;
    }

    public Reply updateReply(Long topicId, Long replyId, UpdateReplyDTO request) {

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reply not found"));

        if (!reply.getTopic().getId().equals(topicId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reply does not belong to the topic");
        }

        reply.setContent(request.getContent());
        reply.setUpdatedAt(LocalDateTime.now());

        return replyRepository.save(reply);
    }

}
