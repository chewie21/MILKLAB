package com.example.milk.repos;

import com.example.milk.domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepo extends CrudRepository <Review, Long> {

    @Query(nativeQuery = true,
            value = "select * from m_review where rating > 3")
    List<Review> findAllByGoodRating();

    @Query(nativeQuery = true,
            value = "select * from m_review where rating = 3")
    List<Review> findAllByNormalRating();

    @Query(nativeQuery = true,
            value = "select * from m_review where rating < 3")
    List<Review> findAllByBadRating();

    @Query(nativeQuery = true,
            value = "select * from m_review where view = true")
    List<Review> findNewReviews();

    @Query(nativeQuery = true,
            value = "select count(*) from m_review where view = true")
    String countNewReviews();

    @Query(nativeQuery = true,
            value = "select * from m_review where username =:username and discount = true limit 1")
    Review countReviewsByUsername (@Param("username") String username);
}
