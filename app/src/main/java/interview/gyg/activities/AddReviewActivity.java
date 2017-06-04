package interview.gyg.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import interview.gyg.R;
import interview.gyg.databinding.ActivityAddReviewBinding;
import interview.gyg.model.AddReviewViewModel;
import interview.gyg.model.Review;

public class AddReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddReviewBinding activityAddReviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_review);
        activityAddReviewBinding.setAddReviewViewModel(new AddReviewViewModel(new Review()));
    }

}
