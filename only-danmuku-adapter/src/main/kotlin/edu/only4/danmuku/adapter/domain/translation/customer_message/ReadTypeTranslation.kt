package edu.only4.danmuku.adapter.domain.translation.customer_message

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.customer_message.enums.ReadType
import org.springframework.stereotype.Component

@TranslationType(type = "customer_message_read_type_code_to_desc")
@Component
class ReadTypeTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val CUSTOMER_MESSAGE_READ_TYPE_CODE_TO_DESC = "customer_message_read_type_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return ReadType.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
