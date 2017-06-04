package interview.gyg.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import interview.gyg.R;
import interview.gyg.api.RestAPIFactory;
import interview.gyg.databinding.ActivityAddReviewBinding;
import interview.gyg.model.AddReviewViewModel;
import interview.gyg.model.Review;

public class AddReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddReviewBinding activityAddReviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_review);
        RestAPIFactory restAPIFactory = new RestAPIFactory();
        AddReviewViewModel addReviewViewModel = new AddReviewViewModel(new Review(), restAPIFactory);
        activityAddReviewBinding.setAddReviewViewModel(addReviewViewModel);
    }

}
