package almostlover.com.viewcollection.bean.db.room;

import androidx.room.*;

import java.util.List;

@Dao
public interface SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSearchBean(RoomSearBean... roomSearBeans);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSearch(RoomSearBean... roomSearBeans);

    @Delete
    void deleteSearchBean(RoomSearBean... roomSearBeans);

    @Query("SELECT * FROM RoomSearBean")
    List<RoomSearBean> loadAllSearchBean();
}
