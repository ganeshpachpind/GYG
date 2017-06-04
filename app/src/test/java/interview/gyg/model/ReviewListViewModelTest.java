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
import retrofit2.HttpException;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
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
        verify(reviewListView, never()).hideProgressBar();
        verify(reviewListView, never()).showErrorMessage();
    }

    @Test
    public void shouldShowErrorMessageOnHttpFailureOfReviewListFetch() throws Exception {

        reviewListViewModel.onError(mock(HttpException.class));

        verify(reviewListView).showErrorMessage();
        verify(reviewListView).hideProgressBar();
        verify(reviewListView, never()).updateReviewList(any(ReviewListResponse.class));
    }

    @Test
    public void shouldShowNetworkErrorMessageWhenNoInternetConnection() throws Exception {

        reviewListViewModel.onError(new Exception());

        verify(reviewListView).showNetworkErrorMessage();
        verify(reviewListView).hideProgressBar();
        verify(reviewListView, never()).updateReviewList(any(ReviewListResponse.class));
    }


    @Test
    public void shouldShowProgressDialogWhileFetchingReviewList() throws Exception {
        reviewListViewModel.getReviewList(1);

        verify(reviewListView).showProgressBar();
    }

    @Test
    public void shouldHideProgressBarOnFetchReviewListCompleted() throws Exception {
        reviewListViewModel.onCompleted();

        verify(reviewListView).hideProgressBar();
        verify(reviewListView, never()).showErrorMessage();
    }
}