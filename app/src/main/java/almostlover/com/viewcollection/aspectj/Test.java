package almostlover.com.viewcollection.aspectj;

public class Test {

    public static  class TestBase{
        static {
            int x = 0;
        }
        int base = 0;

        public TestBase(int base) {
            this.base = base;
        }

        public TestBase() {
        }
    }



    public static class TestDerived extends TestBase{
        public int derived = 0;

        public TestDerived() {
            super(0);
            this.derived = 1000;
        }

        public void testMethod(){
            try{
                byte[] test = null;
                test[1] = 0x33;
            }catch (Exception e){

            }
        }

        static  int getFixedIndex(){
            return  1000;
        }


        public static void main(String args[]){




        }

    }



}
