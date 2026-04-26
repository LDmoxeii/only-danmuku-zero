package edu.only4.danmuku.domain.aggregates.video

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "video_file_variant")
class VideoFileVariant(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "quality")
    val quality: String,
    @Column(name = "width")
    val width: Int,
    @Column(name = "height")
    val height: Int,
    @Column(name = "video_bitrate_kbps")
    val videoBitrateKbps: Int,
    @Column(name = "audio_bitrate_kbps")
    val audioBitrateKbps: Int,
    @Column(name = "bandwidth_bps")
    val bandwidthBps: Int,
    @Column(name = "playlist_path")
    val playlistPath: String,
    @Column(name = "segment_prefix")
    val segmentPrefix: String?,
    @Column(name = "segment_duration")
    val segmentDuration: Int?,
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id", nullable = false)
    lateinit var file: VideoFile

}
