package edu.only4.danmuku.adapter.domain.translation.video_file_post

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.video_post.enums.TransferResult
import org.springframework.stereotype.Component

@TranslationType(type = "video_file_post_transfer_result_code_to_desc")
@Component
class TransferResultTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val VIDEO_FILE_POST_TRANSFER_RESULT_CODE_TO_DESC = "video_file_post_transfer_result_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return TransferResult.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
