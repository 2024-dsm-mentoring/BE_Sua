package org.example.be_sua.domain.feed.service;

import lombok.RequiredArgsConstructor;
import org.example.be_sua.domain.feed.domain.Feed;
import org.example.be_sua.domain.feed.domain.repository.FeedRepository;
import org.example.be_sua.domain.feed.presentation.dto.request.CreateFeedRequest;
import org.example.be_sua.domain.user.domain.User;
import org.example.be_sua.domain.user.domain.repository.UserRepository;
import org.example.be_sua.domain.user.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateFeedService {

    private final FeedRepository feedRepository;
    private final UserRepository userRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public void createFeed(CreateFeedRequest request) {
        User user = userFacade.getCurrentUser();

        Feed feed = feedRepository.save(Feed.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .accountId(request.getAccountId())
                .user(user)
                .build());
    }
}
