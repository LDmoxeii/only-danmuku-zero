package edu.only4.danmuku.adapter.domain.translation.video_hls_key_token

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.video_hls_key_token.enums.EncryptTokenStatus
import org.springframework.stereotype.Component

@TranslationType(type = "video_hls_key_token_encrypt_token_status_code_to_desc")
@Component
class EncryptTokenStatusTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val VIDEO_HLS_KEY_TOKEN_ENCRYPT_TOKEN_STATUS_CODE_TO_DESC = "video_hls_key_token_encrypt_token_status_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return EncryptTokenStatus.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
