package com.esliceu.Myforum.service;

import com.esliceu.Myforum.dto.CreateReplyDTO;
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

}
