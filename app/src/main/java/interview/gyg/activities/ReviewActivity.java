package interview.gyg.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import interview.gyg.R;
import interview.gyg.api.RestAPIFactory;
import interview.gyg.databinding.ActivityReviewBinding;
import interview.gyg.model.Review;
import interview.gyg.model.ReviewListViewModel;
import interview.gyg.repository.ReviewRepository;

public class ReviewActivity extends AppCompatActivity implements ReviewListView {

    private ActivityReviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review);

        ReviewRepository reviewRepository = new ReviewRepository(new RestAPIFactory());
        ReviewListViewModel reviewListViewModel = new ReviewListViewModel(reviewRepository, this);

        binding.setReviewListViewModel(reviewListViewModel);
        reviewListViewModel.getReviewList();
    }

    @Override
    public void setListAdapter(List<Review> reviews) {
        binding.reviewList.setAdapter(new ReviewAdapter(reviews));
        binding.reviewList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void setErrorMessage() {

    }
}
