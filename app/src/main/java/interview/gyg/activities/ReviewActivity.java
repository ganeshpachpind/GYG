package interview.gyg.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review);

        ReviewRepository reviewRepository = new ReviewRepository(new RestAPIFactory());
        final ReviewListViewModel reviewListViewModel = new ReviewListViewModel(reviewRepository, this);
        binding.setReviewListViewModel(reviewListViewModel);

        RecyclerView reviewList = binding.reviewList;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        reviewList.setLayoutManager(layoutManager);
        reviewList.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            public void loadNextPage() {
                currentPageNumber++;
                if (currentPageNumber <= maxPages) {
                    reviewListViewModel.getReviewList(currentPageNumber);
                }
            }
        });
        reviewList.setAdapter(new ReviewAdapter(new ArrayList<Review>()));

        currentPageNumber = 0;
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
    public void setErrorMessage() {

    }
}
