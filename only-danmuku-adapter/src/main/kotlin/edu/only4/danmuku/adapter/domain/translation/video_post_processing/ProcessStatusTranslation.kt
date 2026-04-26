package edu.only4.danmuku.adapter.domain.translation.video_post_processing

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus
import org.springframework.stereotype.Component

@TranslationType(type = "video_post_processing_process_status_code_to_desc")
@Component
class ProcessStatusTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val VIDEO_POST_PROCESSING_PROCESS_STATUS_CODE_TO_DESC = "video_post_processing_process_status_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return ProcessStatus.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
