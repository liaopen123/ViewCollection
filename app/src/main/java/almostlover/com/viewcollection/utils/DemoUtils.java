package almostlover.com.viewcollection.utils;


import java.util.ArrayList;
import java.util.List;

import almostlover.com.viewcollection.bean.DemoItem;

public final class DemoUtils {
  int currentOffset;

  public DemoUtils() {
  }

  public List<DemoItem> moarItems(int qty) {
    List<DemoItem> items = new ArrayList<>();

    for (int i = 0; i < qty; i++) {
//      int colSpan = Math.random() < 0.2f ? 2 : 1;
      DemoItem item;
      if(i==0){
         item = new DemoItem(3, 2, currentOffset + i);
      }else if(i==2||i%7==0){
         item = new DemoItem(2, 2, currentOffset + i);
      }else if(i==12){
         item = new DemoItem(2, 2, currentOffset + i);
      }else{
        item = new DemoItem(1, 1, currentOffset + i);
      }
      // Swap the next 2 lines to have items with variable
      // column/row span.
      // int rowSpan = Math.random() < 0.2f ? 2 : 1;

      items.add(item);
    }

    currentOffset += qty;

    return items;
  }
}
