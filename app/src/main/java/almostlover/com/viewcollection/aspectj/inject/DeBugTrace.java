package almostlover.com.viewcollection.aspectj.inject;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR,ElementType.METHOD})
public @interface DeBugTrace {
}
