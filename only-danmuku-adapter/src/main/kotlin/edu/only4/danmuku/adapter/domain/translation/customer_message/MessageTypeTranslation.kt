package edu.only4.danmuku.adapter.domain.translation.customer_message

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.customer_message.enums.MessageType
import org.springframework.stereotype.Component

@TranslationType(type = "customer_message_message_type_code_to_desc")
@Component
class MessageTypeTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val CUSTOMER_MESSAGE_MESSAGE_TYPE_CODE_TO_DESC = "customer_message_message_type_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return MessageType.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
