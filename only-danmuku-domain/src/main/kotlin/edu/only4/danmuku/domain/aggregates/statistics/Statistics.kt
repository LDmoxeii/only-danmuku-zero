package edu.only4.danmuku.domain.aggregates.statistics

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "statistics")
data class Statistics(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "customer_id")
    val customerId: Long,
    @Column(name = "data_type")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.statistics.enums.StatisticsDataType.Converter::class)
    val dataType: edu.only4.danmuku.domain.aggregates.statistics.enums.StatisticsDataType,
    @Column(name = "statistics_count")
    val statisticsCount: Int?,
    @Column(name = "statistics_date")
    val statisticsDate: Long,
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
)
