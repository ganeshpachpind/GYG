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
import interview.gyg.model.Review;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReviewRepositoryTest extends RxBaseTest {

    @Captor
    ArgumentCaptor<List<Review>> argumentCaptor;

    @Mock
    private RestAPIFactory restAPIFactory;

    @Mock
    private ReviewService reviewService;


    private ReviewRepository reviewRepository;
    private List<Review> reviewList;
    private TestSubscriber<List<Review>> testSubscriber;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        testSubscriber = TestSubscriber.create();
        when(restAPIFactory.getReviewService()).thenReturn(reviewService);
        reviewRepository = new ReviewRepository(restAPIFactory);
        reviewList = Arrays.asList(
                new Review(10, "", "message", "author", false),
                new Review(10, "", "message", "author", false)
        );
    }

    @Test
    public void shouldFetchAllReviewsFromServer() throws Exception {
        when(reviewService.getReviewList()).thenReturn(Observable.just(reviewList));
        reviewRepository.getReviews(testSubscriber);

        verify(restAPIFactory).getReviewService();
        verify(reviewService).getReviewList();

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertReceivedOnNext(Collections.singletonList(reviewList));
    }

    @Test
    public void shouldThrowErrorWhenFetchingReviewFromServerFailed() throws Exception {
        when(reviewService.getReviewList()).thenReturn(Observable.<List<Review>>error(new Exception()));
        reviewRepository.getReviews(testSubscriber);

        testSubscriber.assertError(Exception.class);
        testSubscriber.assertNoValues();
        testSubscriber.assertNotCompleted();

    }
}