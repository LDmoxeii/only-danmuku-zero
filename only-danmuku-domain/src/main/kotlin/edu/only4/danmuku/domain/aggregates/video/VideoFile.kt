package edu.only4.danmuku.domain.aggregates.video

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "video_file")
class VideoFile(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "customer_id")
    val customerId: Long,
    @Column(name = "video_id")
    val videoId: Long,
    @Column(name = "video_file_post_id")
    val videoFilePostId: Long,
    @Column(name = "file_name")
    val fileName: String?,
    @Column(name = "file_index")
    val fileIndex: Int,
    @Column(name = "file_size")
    val fileSize: Long?,
    @Column(name = "file_path")
    val filePath: String?,
    @Column(name = "duration")
    val duration: Int?,
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
    @JoinColumn(name = "file_id", nullable = false)
    var variants: List<VideoFileVariant> = emptyList()
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = false, insertable = false, updatable = false)
    lateinit var video: Video

}
