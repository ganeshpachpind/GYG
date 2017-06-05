package interview.gyg.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import interview.gyg.R;
import interview.gyg.api.RestAPIFactory;
import interview.gyg.databinding.ActivityAddReviewBinding;
import interview.gyg.model.AddReviewViewModel;
import interview.gyg.model.Review;
import interview.gyg.repository.ReviewRepository;

public class AddReviewActivity extends AppCompatActivity implements AddReviewView {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddReviewBinding activityAddReviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_review);
        RestAPIFactory restAPIFactory = new RestAPIFactory();
        Review review = new Review();
        ReviewRepository reviewRepository = new ReviewRepository(restAPIFactory);
        AddReviewViewModel addReviewViewModel = new AddReviewViewModel(review, this, reviewRepository, 1);
        activityAddReviewBinding.setAddReviewViewModel(addReviewViewModel);
        progressBar = activityAddReviewBinding.progressBar;
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
    public void closeActivityWithSuccessMessage() {
        finish();
        Toast.makeText(this, R.string.thank_you, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
