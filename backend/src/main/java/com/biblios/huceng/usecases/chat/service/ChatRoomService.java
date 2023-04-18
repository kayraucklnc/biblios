package com.biblios.huceng.usecases.chat.service;

import java.util.Optional;

public interface ChatRoomService {

    Optional<Long> getChatRoomId(Long senderId, Long recipientId);

}
