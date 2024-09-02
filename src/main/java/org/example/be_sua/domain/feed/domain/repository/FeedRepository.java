package org.example.be_sua.domain.feed.domain.repository;

import org.example.be_sua.domain.feed.domain.Feed;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FeedRepository extends CrudRepository<Feed, Long> {
    Optional<Feed> findById(Long id);
}
