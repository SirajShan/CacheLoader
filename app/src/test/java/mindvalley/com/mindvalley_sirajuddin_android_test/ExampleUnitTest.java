package mindvalley.com.mindvalley_sirajuddin_android_test;

import android.test.AndroidTestCase;
import android.test.mock.MockContext;

import org.junit.Before;
import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest extends AndroidTestCase {
    private MockContext context;

    @Test
    public void test() throws Exception {
        String testJson= new MockContext().getResources().getString(R.string.mock_json);
        assertEquals(4, 2 + 2);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        context = new MockContext();
        setContext(context);
        assertNotNull(context);
    }
}