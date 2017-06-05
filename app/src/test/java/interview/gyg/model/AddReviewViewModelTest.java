package interview.gyg.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import interview.gyg.views.AddReviewView;
import interview.gyg.repository.ReviewRepository;
import retrofit2.HttpException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddReviewViewModelTest {

    @Mock
    AddReviewView addReviewView;
    @Mock
    ReviewRepository reviewRepository;
    private AddReviewViewModel addReviewViewModel;

    @Before
    public void setUp() throws Exception {
        Review review = new Review();
        addReviewViewModel = new AddReviewViewModel(review, addReviewView, reviewRepository, 1);
    }

    @Test
    public void shouldSaveReviewWhenSubmitButtonClicked() throws Exception {

        addReviewViewModel.onSubmitReviewClicked();

        verify(addReviewView).showProgressBar();
        verify(reviewRepository).saveReview(any(AddReviewRequest.class), any(AddReviewViewModel.class));
    }

    @Test
    public void shouldHideProgressBarAndCloseActivityWhenReviewSubmittedSuccess() throws Exception {
        addReviewViewModel.onCompleted();

        verify(addReviewView).hideProgressBar();
        verify(addReviewView).closeActivityWithSuccessMessage();
    }

    @Test
    public void shouldShowErrorMessageOnHttpError() throws Exception {
        addReviewViewModel.onError(mock(HttpException.class));

        verify(addReviewView).hideProgressBar();
        verify(addReviewView).showErrorMessage();

    }


    @Test
    public void shouldShowNoInternetConnectionMessageOnNonHttpError() throws Exception {
        addReviewViewModel.onError(mock(Exception.class));

        verify(addReviewView).hideProgressBar();
        verify(addReviewView).showNetworkErrorMessage();
    }
}