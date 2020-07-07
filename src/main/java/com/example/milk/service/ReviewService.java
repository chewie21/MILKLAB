package com.example.milk.service;

import com.example.milk.domain.Review;
import com.example.milk.domain.User;
import com.example.milk.repos.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    public String dateFormat () {
        Date nowDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        return dateFormat.format(nowDate);
    }

    public void newReview (String username, String name, Long rating, String text) {
        String date = dateFormat();
        Review review = new Review(username, name, date, rating, text);
        if (rating > 3) {
            review.setActive(true);
        }
        review.setDiscount(true);
        review.setView(true);
        reviewRepo.save(review);
    }

    public void deleteReview (Review review) {
        reviewRepo.delete(review);
    }

    public void closeReview (Review review) {
        review.setView(false);
        reviewRepo.save(review);
    }
    public boolean countReviewsByUsername(User user) {
        return reviewRepo.countReviewsByUsername(user.getUsername()) != null;
    }

    public void closeDiscount(User user) {
        Review review = reviewRepo.countReviewsByUsername(user.getUsername());
        if (review != null) {
            review.setDiscount(false);
            reviewRepo.save(review);
        }
    }

    //Count
    public String countNewReviews() {
        return reviewRepo.countNewReviews();
    }

    //Find
    public Iterable<Review> findAll () { return reviewRepo.findAll(); }
    public List<Review> findByGood() {
        return reviewRepo.findAllByGoodRating();
    }
    public List<Review> findByNormal() {
        return reviewRepo.findAllByNormalRating();
    }
    public List<Review> findByBad() {
        return reviewRepo.findAllByBadRating();
    }
    public List<Review> findNewReview() {
        return reviewRepo.findNewReviews();
    }
}
