package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Chat;
import com.example.postgresdemo.repository.ChatRepository;
import com.example.postgresdemo.security.VolcanoUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

@RestController
public class ChatController {

    @Autowired
    private ChatRepository chatRepository;

    @GetMapping("/chats") 
    public List<Chat> getChats() {
        return chatRepository.findAll();
    }

    @PostMapping("/chat")
    public Chat createChat(@Valid @RequestBody Chat chat) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((VolcanoUserPrincipal)principal).getUser().getUserid();
        String userName = ((VolcanoUserPrincipal)principal).getUser().getUsername();
        chat.setCreatorId(userId);
        chat.setCreatorNameSnapshot(userName);
        return chatRepository.save(chat);
    }

}
