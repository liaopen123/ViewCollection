package almostlover.com.viewcollection.bean.db.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RoomSearBean.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SearchDao searchDao();
}
