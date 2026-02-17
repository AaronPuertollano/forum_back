package com.esliceu.Myforum.service;

import com.esliceu.Myforum.dto.CreateTopicDTO;
import com.esliceu.Myforum.model.Category;
import com.esliceu.Myforum.model.Topic;
import com.esliceu.Myforum.model.User;
import com.esliceu.Myforum.repo.CategoryRepository;
import com.esliceu.Myforum.repo.TopicRepository;
import com.esliceu.Myforum.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Topic> getTopicsByCategorySlug(String slug) {

        Category category = categoryRepository.findBySlug(slug)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")
                );

        return topicRepository.findByCategory(category);
    }

    public Topic createTopic(CreateTopicDTO request, String email) {

        Category category = categoryRepository.findBySlug(request.getCategory())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")
                );

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
                );

        Topic topic = new Topic();
        topic.setTitle(request.getTitle());
        topic.setContent(request.getContent());
        topic.setCategory(category);
        topic.setUser(user);
        topic.setViews(0);

        return topicRepository.save(topic);
    }

    public Topic getTopicById(Long id) {

        Topic topic = topicRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found"));

        topic.setViews(topic.getViews() + 1);
        topicRepository.save(topic);

        return topic;
    }
}