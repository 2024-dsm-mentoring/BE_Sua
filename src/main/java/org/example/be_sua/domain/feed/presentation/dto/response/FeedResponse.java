package org.example.be_sua.domain.feed.presentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedResponse {

    private String title;
    private String content;
    private String accountId;
}
