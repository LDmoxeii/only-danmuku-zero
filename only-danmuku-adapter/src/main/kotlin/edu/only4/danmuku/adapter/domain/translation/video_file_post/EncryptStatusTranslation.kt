package edu.only4.danmuku.adapter.domain.translation.video_file_post

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptStatus
import org.springframework.stereotype.Component

@TranslationType(type = "video_file_post_encrypt_status_code_to_desc")
@Component
class EncryptStatusTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val VIDEO_FILE_POST_ENCRYPT_STATUS_CODE_TO_DESC = "video_file_post_encrypt_status_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return EncryptStatus.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
