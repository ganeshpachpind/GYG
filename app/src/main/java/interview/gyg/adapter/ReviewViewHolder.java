package interview.gyg.adapter;


import android.support.v7.widget.RecyclerView;

import interview.gyg.databinding.ReviewBinding;
import interview.gyg.model.Review;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private final ReviewBinding reviewBinding;

    public ReviewViewHolder(ReviewBinding reviewBinding) {
        super(reviewBinding.getRoot());
        this.reviewBinding = reviewBinding;
    }

    public void bind(Review review) {
        reviewBinding.setReview(review);
        reviewBinding.executePendingBindings();
    }
}
