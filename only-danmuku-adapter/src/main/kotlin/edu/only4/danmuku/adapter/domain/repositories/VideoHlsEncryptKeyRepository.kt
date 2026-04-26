package edu.only4.danmuku.adapter.domain.repositories

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.domain.repo.AbstractJpaRepository
import edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.VideoHlsEncryptKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
interface VideoHlsEncryptKeyRepository : JpaRepository<VideoHlsEncryptKey, Long>, JpaSpecificationExecutor<VideoHlsEncryptKey> {

    @Component
    @Aggregate(aggregate = "VideoHlsEncryptKey", name = "VideoHlsEncryptKeyRepo", type = Aggregate.TYPE_REPOSITORY, description = "")
    class VideoHlsEncryptKeyJpaRepositoryAdapter(
        jpaSpecificationExecutor: JpaSpecificationExecutor<VideoHlsEncryptKey>,
        jpaRepository: JpaRepository<VideoHlsEncryptKey, Long>
    ) : AbstractJpaRepository<VideoHlsEncryptKey, Long>(
        jpaSpecificationExecutor,
        jpaRepository
    )
}
