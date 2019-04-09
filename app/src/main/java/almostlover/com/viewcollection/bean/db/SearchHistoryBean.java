package almostlover.com.viewcollection.bean.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SearchHistoryBean {

    @Id(autoincrement = true)
    private Long id;
    private String searchContent;//搜索记录

    @Generated(hash = 282610569)
    public SearchHistoryBean(Long id, String searchContent) {
        this.id = id;
        this.searchContent = searchContent;
    }

    @Generated(hash = 1570282321)
    public SearchHistoryBean() {
    }

    public String getSearchContent() {
        return this.searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    @Override
    public String toString() {
        return "SearchHistoryBean{" +
                "id=" + id +
                ", searchContent='" + searchContent + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
