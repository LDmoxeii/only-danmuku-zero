package edu.only4.danmuku.domain.aggregates.video

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "video")
class Video(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "video_post_id")
    val videoPostId: Long,
    @Column(name = "customer_id")
    val customerId: Long,
    @Column(name = "video_cover")
    val videoCover: String,
    @Column(name = "video_name")
    val videoName: String,
    @Column(name = "p_category_id")
    val pCategoryId: Long,
    @Column(name = "category_id")
    val categoryId: Long?,
    @Column(name = "post_type")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_post.enums.PostType.Converter::class)
    val postType: edu.only4.danmuku.domain.aggregates.video_post.enums.PostType,
    @Column(name = "origin_info")
    val originInfo: String?,
    @Column(name = "tags")
    val tags: String?,
    @Column(name = "introduction")
    val introduction: String?,
    @Column(name = "interaction")
    val interaction: String?,
    @Column(name = "duration")
    val duration: Int,
    @Column(name = "play_count")
    val playCount: Int,
    @Column(name = "like_count")
    val likeCount: Int,
    @Column(name = "danmuku_count")
    val danmukuCount: Int,
    @Column(name = "comment_count")
    val commentCount: Int,
    @Column(name = "coin_count")
    val coinCount: Int,
    @Column(name = "collect_count")
    val collectCount: Int,
    @Column(name = "recommend_type")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video.enums.RecommendType.Converter::class)
    val recommendType: edu.only4.danmuku.domain.aggregates.video.enums.RecommendType,
    @Column(name = "last_play_time")
    val lastPlayTime: Long?,
    @Column(name = "create_user_id")
    val createUserId: Long?,
    @Column(name = "create_by")
    val createBy: String?,
    @Column(name = "create_time")
    val createTime: Long?,
    @Column(name = "update_user_id")
    val updateUserId: Long?,
    @Column(name = "update_by")
    val updateBy: String?,
    @Column(name = "update_time")
    val updateTime: Long?,
    @Column(name = "deleted")
    val deleted: Long
) {
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "video_id", nullable = false)
    var files: List<VideoFile> = emptyList()
}
