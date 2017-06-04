package interview.gyg.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import interview.gyg.R;
import interview.gyg.adapter.ReviewAdapter;
import interview.gyg.api.RestAPIFactory;
import interview.gyg.databinding.ActivityReviewBinding;
import interview.gyg.model.Review;
import interview.gyg.model.ReviewListResponse;
import interview.gyg.model.ReviewListViewModel;
import interview.gyg.repository.ReviewRepository;

public class ReviewActivity extends AppCompatActivity implements ReviewListView {

    private ActivityReviewBinding binding;
    private int currentPageNumber = 0;
    private int maxPages = 1;
    private ProgressBar progressBar;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review);

        ReviewRepository reviewRepository = new ReviewRepository(new RestAPIFactory());
        final ReviewListViewModel reviewListViewModel = new ReviewListViewModel(reviewRepository, this);
        binding.setReviewListViewModel(reviewListViewModel);
        progressBar = binding.progressBar;
        RecyclerView reviewList = binding.reviewList;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        reviewList.setLayoutManager(layoutManager);
        reviewList.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            public void loadNextPage() {
                if (!isLoading) {
                    currentPageNumber++;
                    if (currentPageNumber <= maxPages) {
                        reviewListViewModel.getReviewList(currentPageNumber);
                    }
                }
            }
        });
        reviewList.setAdapter(new ReviewAdapter(new ArrayList<Review>()));

        reviewListViewModel.getReviewList(currentPageNumber);

    }

    @Override
    public void updateReviewList(ReviewListResponse reviewListResponse) {
        maxPages = reviewListResponse.getTotalReviews() / 10;
        ReviewAdapter adapter = (ReviewAdapter) binding.reviewList.getAdapter();
        adapter.addAll(reviewListResponse.getReviews());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        isLoading = false;
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNetworkErrorMessage() {
        Toast.makeText(this, R.string.check_internet, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToAddReview() {
        Intent addReviewActivity = new Intent(this, AddReviewActivity.class);
        startActivity(addReviewActivity);
    }
}
