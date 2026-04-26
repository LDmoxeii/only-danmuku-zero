package edu.only4.danmuku.domain.aggregates.customer_video_series

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "customer_video_series_video")
class CustomerVideoSeriesVideo(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "customer_id")
    val customerId: Long,
    @Column(name = "video_id")
    val videoId: Long,
    @Column(name = "sort")
    val sort: Int,
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
    @JoinColumn(name = "series_id", nullable = false)
    lateinit var series: CustomerVideoSeries

}
