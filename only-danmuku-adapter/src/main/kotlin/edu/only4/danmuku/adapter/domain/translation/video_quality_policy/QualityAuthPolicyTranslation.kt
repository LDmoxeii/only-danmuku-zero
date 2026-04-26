package edu.only4.danmuku.adapter.domain.translation.video_quality_policy

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.video_quality_policy.enums.QualityAuthPolicy
import org.springframework.stereotype.Component

@TranslationType(type = "video_quality_policy_quality_auth_policy_code_to_desc")
@Component
class QualityAuthPolicyTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val VIDEO_QUALITY_POLICY_QUALITY_AUTH_POLICY_CODE_TO_DESC = "video_quality_policy_quality_auth_policy_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return QualityAuthPolicy.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
