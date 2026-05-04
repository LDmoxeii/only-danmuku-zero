package edu.only4.danmuku.application.queries._share.model

import java.util.UUID

import edu.only4.danmuku.domain._share.enums.EncryptMethod
import edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.enums.EncryptKeyStatus
import org.babyfish.jimmer.sql.Column
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Table

@Entity
@Table(name = "video_hls_encrypt_key")
interface VideoHlsEncryptKey : BaseEntity {

    @Column(name = "video_post_id")
    val videoPostId: UUID

    @Column(name = "video_id")
    val videoId: UUID?

    @Column(name = "file_index")
    val fileIndex: Int

    @Column(name = "quality")
    val quality: String

    @Column(name = "key_id")
    val keyId: String

    @Column(name = "key_ciphertext")
    val keyCiphertext: String

    @Column(name = "iv_hex")
    val ivHex: String?

    @Column(name = "key_version")
    val keyVersion: Int

    @Column(name = "method")
    val method: EncryptMethod

    @Column(name = "key_uri_template")
    val keyUriTemplate: String

    @Column(name = "expire_time")
    val expireTime: Long?

    @Column(name = "status")
    val status: EncryptKeyStatus

    @Column(name = "remark")
    val remark: String?
}

