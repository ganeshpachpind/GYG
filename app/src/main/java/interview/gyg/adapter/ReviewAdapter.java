package interview.gyg.adapter;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import interview.gyg.R;
import interview.gyg.databinding.ReviewBinding;
import interview.gyg.model.Review;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    private List<Review> reviewList;

    public ReviewAdapter(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ReviewBinding reviewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.review, parent, false);
        return new ReviewViewHolder(reviewBinding);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.bind(reviewList.get(position));
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public void addAll(List<Review> reviews) {
        for(Review review: reviews){
            this.reviewList.add(review);
        }
    }
}
