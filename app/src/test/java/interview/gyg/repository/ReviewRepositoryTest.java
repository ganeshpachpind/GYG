package interview.gyg.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import interview.gyg.api.RestAPIFactory;
import interview.gyg.api.ReviewService;
import interview.gyg.helper.RxBaseTest;
import interview.gyg.model.AddReviewRequest;
import interview.gyg.model.Review;
import interview.gyg.model.ReviewListResponse;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReviewRepositoryTest extends RxBaseTest {

    @Captor
    ArgumentCaptor<ReviewListResponse> argumentCaptor;

    @Mock
    private RestAPIFactory restAPIFactory;

    @Mock
    private ReviewService reviewService;


    private ReviewRepository reviewRepository;
    private List<Review> reviewList;
    ReviewListResponse reviewListResponse;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        reviewListResponse = new ReviewListResponse(true, 300, reviewList);
        when(restAPIFactory.getReviewService()).thenReturn(reviewService);
        reviewRepository = new ReviewRepository(restAPIFactory);
        reviewList = Arrays.asList(
                new Review(10, "", "message", "author", false),
                new Review(10, "", "message", "author", false)
        );
    }

    @Test
    public void shouldFetchAllReviewsFromServer() throws Exception {
        TestSubscriber<ReviewListResponse> testSubscriber = TestSubscriber.create();
        when(reviewService.getReviewList(0)).thenReturn(Observable.just(reviewListResponse));
        reviewRepository.getReviews(0, testSubscriber);

        verify(restAPIFactory).getReviewService();
        verify(reviewService).getReviewList(0);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertReceivedOnNext(Collections.singletonList(reviewListResponse));
    }

    @Test
    public void shouldThrowErrorWhenFetchingReviewFromServerFailed() throws Exception {
        TestSubscriber<ReviewListResponse> testSubscriber = TestSubscriber.create();
        when(reviewService.getReviewList(0)).thenReturn(Observable.<ReviewListResponse>error(new Exception()));
        reviewRepository.getReviews(0, testSubscriber);

        testSubscriber.assertError(Exception.class);
        testSubscriber.assertNoValues();
        testSubscriber.assertNotCompleted();

    }

    @Test
    public void shouldSaveReviewToServer() throws Exception {
        TestSubscriber<Review> saveSubscriber = TestSubscriber.create();
        Review review = new Review();
        AddReviewRequest addReviewRequest = new AddReviewRequest(1, review);
        when(reviewService.submitReview(addReviewRequest)).thenReturn(Observable.just(review));

        reviewRepository.saveReview(addReviewRequest, saveSubscriber);

        verify(restAPIFactory).getReviewService();

        saveSubscriber.assertNoErrors();
        saveSubscriber.assertCompleted();
        saveSubscriber.assertReceivedOnNext(Collections.singletonList(review));

    }

    @Test
    public void shouldThrowErrorWhenSaveReviewToServerFails() throws Exception {
        TestSubscriber<Review> saveSubscriber = TestSubscriber.create();
        Review review = new Review();
        AddReviewRequest addReviewRequest = new AddReviewRequest(1, review);
        when(reviewService.submitReview(addReviewRequest)).thenReturn(Observable.<Review>error(new Exception()));

        reviewRepository.saveReview(addReviewRequest, saveSubscriber);

        verify(restAPIFactory).getReviewService();

        saveSubscriber.assertNotCompleted();
        saveSubscriber.assertNoValues();
        saveSubscriber.assertNotCompleted();

    }
}