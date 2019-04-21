package almostlover.com.viewcollection.bean.db.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class RoomSearBean {
    @PrimaryKey(autoGenerate = true)
    public  int id;

    public String searchContent;

    public RoomSearBean(String searchContent) {
        this.searchContent = searchContent;
    }

    @Override
    public String toString() {
        return "RoomSearBean{" +
                "id=" + id +
                ", searchContent='" + searchContent + '\'' +
                '}';
    }
}
