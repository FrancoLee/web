import com.lx.animation.util.AddressUtil;
import org.junit.Test;

/**
 * Created by root on 18-8-5.
 */
public class StringUtilTest {
    @Test
    public void test() {
        String str1 = new String("hello");
        String str2 = new String("hello");

        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));
    }
}
