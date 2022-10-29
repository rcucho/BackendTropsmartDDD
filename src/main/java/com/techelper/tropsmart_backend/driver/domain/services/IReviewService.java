package com.techelper.tropsmart_backend.driver.domain.services;

import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;
import com.techelper.tropsmart_backend.driver.domain.models.Review;
import com.techelper.tropsmart_backend.driver.domain.services.comunications.ReviewResponse;
import com.techelper.tropsmart_backend.driver.domain.services.inputs.ReviewInput;

public interface IReviewService extends ICrudService<Review> {
    ReviewResponse findAllReviews();
    ReviewResponse findReviewsByCustomerId(int customerId);
    ReviewResponse findReviewsByDriverId(int driverId);
    ReviewResponse findReviewById(int reviewId);
    ReviewResponse addReviewByCargoId(int cargoId, ReviewInput reviewInput);
}
