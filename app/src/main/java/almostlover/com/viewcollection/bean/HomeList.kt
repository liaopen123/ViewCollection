package almostlover.com.viewcollection.bean

import cn.almostlover.liaomvp.mvp.model.bean.BaseBean

data class HomeList(
    val `data`: Data
) : BaseBean(){
    data class Data(
        val dining_table: DiningTable,
        val hot_diy: HotDiy,
        val hot_topic: HotTopic,
        val inter_farm: InterFarm,
        val private_custom: PrivateCustom,
        val social_circle: SocialCircle,
        val xuyuan_riji: XuyuanRiji
    ) {
        data class DiningTable(
            val table_list: List<Table>,
            val table_title: TableTitle
        ) {
            data class Table(
                val article_id: Int,
                val bind_goods: BindGoods,
                val collected: Int,
                val content: String,
                val cover: String,
                val member_id: Int,
                val title: String
            ) {
                data class BindGoods(
                    val goods_id: Int,
                    val goods_name: String
                )
            }

            data class TableTitle(
                val activity_id: Int,
                val activity_name: String,
                val activity_subheading: String
            )
        }

        data class HotDiy(
            val diy_list: List<Diy>,
            val diy_title: DiyTitle
        ) {
            data class Diy(
                val article_id: Int,
                val bind_goods: Any,
                val collected: Int,
                val content: String,
                val cover: String,
                val member_id: Int,
                val title: String
            )

            data class DiyTitle(
                val activity_id: Int,
                val activity_name: String,
                val activity_subheading: String
            )
        }

        data class HotTopic(
            val topic_list: List<Topic>,
            val topic_title: TopicTitle
        ) {
            data class Topic(
                val article_id: Int,
                val check_status: Int,
                val collect_num: Int,
                val collected: Int,
                val cover: String,
                val cover_height: Int,
                val cover_width: Int,
                val head_pic: String,
                val member_id: Int,
                val nickname: String,
                val store_desc: String,
                val title: String,
                val user_id: Int,
                val user_type: Int
            )

            data class TopicTitle(
                val activity_id: Int,
                val activity_name: String,
                val activity_subheading: String
            )
        }

        data class InterFarm(
            val farm_list: List<Farm>,
            val farm_title: FarmTitle
        ) {
            data class Farm(
                val farm_climate: FarmClimate,
                val farm_cover: String,
                val farm_id: Int,
                val farm_name: String,
                val farm_recommend_goods_id: String,
                val goods_detial: GoodsDetial,
                val project_desc: String,
                val project_img: String,
                val project_title: String
            ) {
                data class FarmClimate(
                    val air_humidity: Double,
                    val air_temp: Double,
                    val carbon_dioxide: Double,
                    val end_date: String,
                    val farm_id: Int,
                    val light_strong: Double,
                    val soil_humidity: Double,
                    val soil_temp: Double
                )

                data class GoodsDetial(
                    val goods_id: Int,
                    val goods_name: String,
                    val goods_price: Double,
                    val goods_spec_prices: GoodsSpecPrices,
                    val goods_thumb: String,
                    val store_id: Int
                ) {
                    data class GoodsSpecPrices(
                        val goods_id: Int,
                        val min_spec_price: Double,
                        val spec_img: Any,
                        val spec_item_id: String,
                        val spec_item_name: String
                    )
                }
            }

            data class FarmTitle(
                val activity_id: Int,
                val activity_name: String,
                val activity_subheading: String
            )
        }

        data class PrivateCustom(
            val custom_list: List<Custom>,
            val custom_title: CustomTitle
        ) {
            data class Custom(
                val article_id: Int,
                val bind_goods: Any,
                val collected: Int,
                val content: String,
                val cover: String,
                val member_id: Int,
                val title: String
            )

            data class CustomTitle(
                val activity_id: Int,
                val activity_name: String,
                val activity_subheading: String
            )
        }

        data class SocialCircle(
            val social_list: List<Social>,
            val social_title: SocialTitle
        ) {
            data class Social(
                val article_id: Int,
                val collect_num: Int,
                val commentCount: Int,
                val cover: String,
                val head_pic: String,
                val like_num: Int,
                val nickname: String,
                val title: String,
                val view_count: Int
            )

            data class SocialTitle(
                val activity_id: Int,
                val activity_name: String,
                val activity_subheading: String
            )
        }

        data class XuyuanRiji(
            val xunyuan_list: List<Xunyuan>,
            val xunyuan_title: XunyuanTitle
        ) {
            data class Xunyuan(
                val article_id: Int,
                val check_status: Int,
                val collect_num: Int,
                val collected: Int,
                val cover: String,
                val cover_height: Int,
                val cover_width: Int,
                val head_pic: String,
                val member_id: Int,
                val nickname: String,
                val store_desc: Any,
                val title: String,
                val user_id: Int,
                val user_type: Int
            )

            data class XunyuanTitle(
                val activity_id: Int,
                val activity_name: String,
                val activity_subheading: String
            )
        }
    }
}