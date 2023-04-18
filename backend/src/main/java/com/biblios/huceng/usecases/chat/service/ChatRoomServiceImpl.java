package com.biblios.huceng.usecases.chat.service;

import com.biblios.huceng.entity.ChatRoom;
import com.biblios.huceng.entity.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository repository;


    @Override
    public Optional<Long> getChatRoomId(Long senderId, Long recipientId) {
        return repository
                .findByOwnerIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getId)
                .or(() -> {
                    ChatRoom room = ChatRoom.builder()
                            .ownerId(senderId)
                            .recipientId(recipientId)
                            .build();
                    return Optional.of(repository.save(room).getId());
                });
    }
}
