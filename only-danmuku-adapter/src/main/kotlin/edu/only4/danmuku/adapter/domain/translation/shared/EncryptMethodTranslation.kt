package edu.only4.danmuku.adapter.domain.translation.shared

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptMethod
import org.springframework.stereotype.Component

@TranslationType(type = "encrypt_method_code_to_desc")
@Component
class EncryptMethodTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val ENCRYPT_METHOD_CODE_TO_DESC = "encrypt_method_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return EncryptMethod.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
