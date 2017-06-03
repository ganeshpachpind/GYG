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
        reviewListViewModel.getReviewList();

        verify(reviewRepository).getReviews(reviewListViewModel);
    }

    @Test
    public void shouldSetListAdapterOnSuccessfulReviewListFetch() throws Exception {

        List<Review> reviews = Arrays.asList(
                new Review(10, "", "message", "author", false),
                new Review(10, "", "message", "author", false)
        );

        reviewListViewModel.onNext(reviews);

        verify(reviewListView).setListAdapter(reviews);
    }

    @Test
    public void shouldSetErrorMessageOnFailureOfReviewListFetch() throws Exception {

        reviewListViewModel.onError(new Exception());

        verify(reviewListView).setErrorMessage();
    }
}