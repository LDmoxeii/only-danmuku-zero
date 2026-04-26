package edu.only4.danmuku.adapter.domain.translation.video_file_upload_session

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.enums.UploadStatus
import org.springframework.stereotype.Component

@TranslationType(type = "video_file_upload_session_upload_status_code_to_desc")
@Component
class UploadStatusTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val VIDEO_FILE_UPLOAD_SESSION_UPLOAD_STATUS_CODE_TO_DESC = "video_file_upload_session_upload_status_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return UploadStatus.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
