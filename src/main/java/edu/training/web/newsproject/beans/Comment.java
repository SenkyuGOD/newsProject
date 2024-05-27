package edu.training.web.newsproject.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer commentId;
    private Integer newsId;
    private Integer userId;
    private String commentText;
    private LocalDateTime createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) &&
                Objects.equals(newsId, comment.newsId) &&
                Objects.equals(userId, comment.userId) &&
                Objects.equals(commentText, comment.commentText) &&
                Objects.equals(createdDate, comment.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, newsId, userId, commentText, createdDate);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", newsId=" + newsId +
                ", userId=" + userId +
                ", commentText='" + commentText + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}

