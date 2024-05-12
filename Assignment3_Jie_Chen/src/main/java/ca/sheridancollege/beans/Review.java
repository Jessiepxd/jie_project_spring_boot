package ca.sheridancollege.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Review {
    private Long reviewId;
    @NonNull
    private String content;
    @NonNull
    private Book book;
    @NonNull
    private User user;
}
