package edu.only4.danmuku.adapter.domain.translation.video_audit_trace

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.video_audit_trace.enums.AuditStatus
import org.springframework.stereotype.Component

@TranslationType(type = "video_audit_trace_audit_status_code_to_desc")
@Component
class AuditStatusTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val VIDEO_AUDIT_TRACE_AUDIT_STATUS_CODE_TO_DESC = "video_audit_trace_audit_status_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return AuditStatus.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
