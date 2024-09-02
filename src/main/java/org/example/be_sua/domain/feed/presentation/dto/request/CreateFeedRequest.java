package org.example.be_sua.domain.feed.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateFeedRequest {
    private String accountId;
    private String title;
    private String content;
}
