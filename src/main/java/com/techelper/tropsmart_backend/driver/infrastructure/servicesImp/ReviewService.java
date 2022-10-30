package com.techelper.tropsmart_backend.driver.infrastructure.servicesImp;

import com.techelper.tropsmart_backend.cargo.domain.models.Cargo;
import com.techelper.tropsmart_backend.cargo.domain.repositories.ICargoRepository;
import com.techelper.tropsmart_backend.driver.domain.models.Qualification;
import com.techelper.tropsmart_backend.driver.domain.models.Review;
import com.techelper.tropsmart_backend.driver.domain.repositories.IQualificationRepository;
import com.techelper.tropsmart_backend.driver.domain.repositories.IReviewRepository;
import com.techelper.tropsmart_backend.driver.domain.services.IReviewService;
import com.techelper.tropsmart_backend.driver.domain.services.comunications.ReviewResponse;
import com.techelper.tropsmart_backend.driver.domain.services.inputs.ReviewInput;
import com.techelper.tropsmart_backend.driver.domain.services.outputs.ReviewOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements IReviewService {
    @Autowired
    private IReviewRepository reviewRepository;
    @Autowired
    private ICargoRepository cargoRepository;
    @Autowired
    private IQualificationRepository qualificationRepository;

    @Override
    public ReviewResponse findAllReviews() {
        try
        {
            List<Review> reviews = reviewRepository.findAll();
            List<ReviewOutput> reviewOutputList = new ArrayList<>();
            for (Review r:reviews) {
                ReviewOutput newReviewOutput = new ReviewOutput();
                newReviewOutput.setCustomer(r.getCargo().getCustomer().getPerson().getFirstName()+" "+r.getCargo().getCustomer().getPerson().getLastName());
                newReviewOutput.setDriver(r.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+r.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
                newReviewOutput.setCargo(r.getCargo().getDescription());
                newReviewOutput.setCommentary(r.getCommentary());
                newReviewOutput.setCalification(r.getCalification());
                reviewOutputList.add(newReviewOutput);
            }
            return new ReviewResponse(reviewOutputList);
        }
        catch (Exception e)
        {
            return new ReviewResponse("An error ocurred while getting the review list : "+e.getMessage());
        }
    }


    @Override
    public ReviewResponse findReviewsByCustomerId(int customerId) {
        try
        {
            List<Review> reviews = reviewRepository.findReviewsByCustomerId(customerId);
            List<ReviewOutput> reviewOutputList = new ArrayList<>();
            for (Review r:reviews) {
                ReviewOutput newReviewOutput = new ReviewOutput();
                newReviewOutput.setCustomer(r.getCargo().getCustomer().getPerson().getFirstName()+" "+r.getCargo().getCustomer().getPerson().getLastName());
                newReviewOutput.setDriver(r.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+r.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
                newReviewOutput.setCargo(r.getCargo().getDescription());
                newReviewOutput.setCommentary(r.getCommentary());
                newReviewOutput.setCalification(r.getCalification());
                reviewOutputList.add(newReviewOutput);
            }
            return new ReviewResponse(reviewOutputList);
        }
        catch (Exception e)
        {
            return new ReviewResponse("An error ocurred while getting the review list : "+e.getMessage());
        }
    }

    @Override
    public ReviewResponse findReviewsByDriverId(int driverId) {
        try
        {
            List<Review> reviews = reviewRepository.findReviewsByDriverId(driverId);
            List<ReviewOutput> reviewOutputList = new ArrayList<>();
            for (Review r:reviews) {
                ReviewOutput newReviewOutput = new ReviewOutput();
                newReviewOutput.setCustomer(r.getCargo().getCustomer().getPerson().getFirstName()+" "+r.getCargo().getCustomer().getPerson().getLastName());
                newReviewOutput.setDriver(r.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+r.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
                newReviewOutput.setCargo(r.getCargo().getDescription());
                newReviewOutput.setCommentary(r.getCommentary());
                newReviewOutput.setCalification(r.getCalification());
                reviewOutputList.add(newReviewOutput);
            }
            return new ReviewResponse(reviewOutputList);
        }
        catch (Exception e)
        {
            return new ReviewResponse("An error ocurred while getting the review list : "+e.getMessage());
        }
    }

    @Override
    public ReviewResponse findReviewById(int reviewId) {
        try
        {
            Review getReview = reviewRepository.findById(reviewId).get();
            ReviewOutput newReviewOutput = new ReviewOutput();
            newReviewOutput.setCustomer(getReview.getCargo().getCustomer().getPerson().getFirstName()+" "+getReview.getCargo().getCustomer().getPerson().getLastName());
            newReviewOutput.setDriver(getReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+getReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
            newReviewOutput.setCargo(getReview.getCargo().getDescription());
            newReviewOutput.setCommentary(getReview.getCommentary());
            newReviewOutput.setCalification(getReview.getCalification());
            return new ReviewResponse(newReviewOutput);
        }
        catch (Exception e)
        {
            return new ReviewResponse("An error ocurred while getting the review : "+e.getMessage());

        }

    }

    @Override
    public ReviewResponse addReviewByCargoId(int cargoId, ReviewInput reviewInput) {
        try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();
            Qualification getQualification = qualificationRepository.findQualificationByDriverId(getCargo.getService().getServicesRequest().getDriver().getId());
            Review newReview = new Review();
            newReview.setCommentary(reviewInput.getCommentary());
            newReview.setCalification(reviewInput.getCalification());
            newReview.setCargo(getCargo);
            newReview.setQualification(getQualification);

            newReview = reviewRepository.save(newReview);

            ReviewOutput newReviewOutput = new ReviewOutput();
            newReviewOutput.setCustomer(newReview.getCargo().getCustomer().getPerson().getFirstName()+" "+newReview.getCargo().getCustomer().getPerson().getLastName());
            newReviewOutput.setDriver(newReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+newReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
            newReviewOutput.setCargo(newReview.getCargo().getDescription());
            newReviewOutput.setCommentary(newReview.getCommentary());
            newReviewOutput.setCalification(newReview.getCalification());
            return new ReviewResponse(newReviewOutput);
        }
        catch (Exception e)
        {
            return new ReviewResponse("An error ocurred while registering the review : "+e.getMessage());
        }
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteById(Integer id){
        reviewRepository.deleteById(id);
    }

    @Override
    public Optional<Review> findById(Integer id){
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> findAll(){
        return reviewRepository.findAll();
    }
}