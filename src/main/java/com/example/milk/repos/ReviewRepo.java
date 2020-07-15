package com.example.milk.repos;

import com.example.milk.domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepo extends CrudRepository <Review, Long> {

    @Query(nativeQuery = true,
            value = "select * from m_review order by id DESC")
    List<Review> findAll();

    @Query(nativeQuery = true,
            value = "select * from m_review where rating > 3 order by id DESC")
    List<Review> findAllByGoodRating();

    @Query(nativeQuery = true,
            value = "select * from m_review where rating = 3 order by id DESC")
    List<Review> findAllByNormalRating();

    @Query(nativeQuery = true,
            value = "select * from m_review where rating < 3 order by id DESC")
    List<Review> findAllByBadRating();

    @Query(nativeQuery = true,
            value = "select * from m_review where view = true order by id DESC")
    List<Review> findNewReviews();

    @Query(nativeQuery = true,
            value = "select count(*) from m_review where view = true")
    String countNewReviews();

    @Query(nativeQuery = true,
            value = "select * from m_review where username =:username and discount = true limit 1")
    Review countReviewsByUsername (@Param("username") String username);

    @Query(nativeQuery = true,
            value = "select * from m_review where rating > 3 order by id DESC LIMIT 3")
    List<Review> findAllByGoodRatingFirst();


    @Query(nativeQuery = true,
            value = "select * from m_review where rating > 3 order by id DESC LIMIT 3, 100")
    List<Review> findAllByGoodRatingSecond();
}
