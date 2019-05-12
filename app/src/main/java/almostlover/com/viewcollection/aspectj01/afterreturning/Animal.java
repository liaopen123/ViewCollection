package almostlover.com.viewcollection.aspectj01.afterreturning;

public class Animal {
    public int getHeight() {
        return 0;
    }

    public int getHeight(int sex) {
        switch (sex) {
            case 0:
                return 163;
            case 1:
                return 173;
        }
        return 173;
    }
}