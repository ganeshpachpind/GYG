package interview.gyg.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import interview.gyg.activities.ReviewListView;
import interview.gyg.repository.ReviewRepository;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReviewListViewModelTest {

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    ReviewListView reviewListView;

    private ReviewListViewModel reviewListViewModel;

    @Before
    public void setUp() throws Exception {
        reviewListViewModel = new ReviewListViewModel(reviewRepository, reviewListView);
    }

    @Test
    public void shouldFetchReviewListFromRepository() throws Exception {
        reviewListViewModel.getReviewList(0);

        verify(reviewRepository).getReviews(0, reviewListViewModel);
    }

    @Test
    public void shouldSetListAdapterOnSuccessfulReviewListFetch() throws Exception {

        List<Review> reviews = Arrays.asList(
                new Review(10f, "", "message", "author", false),
                new Review(10, "", "message", "author", false)
        );

        ReviewListResponse reviewListResponse = new ReviewListResponse(true, 200, reviews);
        reviewListViewModel.onNext(reviewListResponse);

        verify(reviewListView).updateReviewList(reviewListResponse);
    }

    @Test
    public void shouldSetErrorMessageOnFailureOfReviewListFetch() throws Exception {

        reviewListViewModel.onError(new Exception());

        verify(reviewListView).setErrorMessage();
    }
}