package org.example.be_sua.domain.feed.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.be_sua.domain.user.domain.User;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_table")
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    @ColumnDefault("-")
    @Column(name = "title")
    private String title;

    @ColumnDefault("-")
    @Column(name = "content")
    private String content;

    @Column(name = "accountId")
    private String accountId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Feed(String title, String content, String accountId, User user) {
        this.title = title;
        this.content = content;
        this.accountId = accountId;
        this.user = user;
    }
}
