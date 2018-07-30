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

    @GetMapping("/team/{teamId}/chats")
    public List<Chat> getTeamScopedChats(@PathVariable("teamId") Long teamId) {
        List<Chat> chats = chatRepository.findByTeamIdOrderByChatId(teamId);
        return chats;
    }

    @GetMapping("/chats")
    public List<Chat> getChats() {
        return chatRepository.findAll();
    }

    @PostMapping("/team/{teamId}/chat")
    public Chat createChatInTeam(@Valid @RequestBody Chat chat, @PathVariable("teamId") Long teamId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((VolcanoUserPrincipal)principal).getUser().getUserid();
        String userName = ((VolcanoUserPrincipal)principal).getUser().getUsername();
        chat.setCreatorId(userId);
        chat.setCreatorNameSnapshot(userName);
        chat.setTeamId(teamId);
        return chatRepository.save(chat);
    }
//Start here for private chat idea
    @GetMapping ("user/{userId}/chat/privatemessages/{userId2}")
    public List<Chat> getUserPrivateChats(@PathVariable("userId") Long userId, @PathVariable("userId2") Long userId2){
        // This needs to return the list of chats to a given user
        List<Chat> chats = chatRepository.findByTeamIdOrderByChatId(userId);
        return  chats;
    }

    @PostMapping("/user/{userId}/chat/privatemessages/{userId2}")
    public Chat createPrivateChat(@Valid @RequestBody Chat chat, @PathVariable("userId") Long UserId, @PathVariable("userId2") Long userId2) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((VolcanoUserPrincipal)principal).getUser().getUserid();
        String userName = ((VolcanoUserPrincipal)principal).getUser().getUsername();
        chat.setCreatorId(userId);
        chat.setCreatorNameSnapshot(userName);
        // May not need...
        //chat.setTeamId(teamId);
        return chatRepository.save(chat);
    }

    @PostMapping("/chat")
    public Chat createChat(@Valid @RequestBody Chat chat) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((VolcanoUserPrincipal)principal).getUser().getUserid();
        String userName = ((VolcanoUserPrincipal)principal).getUser().getUsername();
        chat.setCreatorId(userId);
        chat.setCreatorNameSnapshot(userName);
        chat.getTimeSent();
        return chatRepository.save(chat);
    }
/*
    @DeleteMapping("/chats/{chatId}")
    public ResponseEntity<?> deleteChat(@PathVariable Long chatId) {
        return chatRepository.findByChatId(chatId)
                .map(chat -> {
                    chatRepository.delete(chat);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Chat not found with id " + chatId));
    }
    */
}
