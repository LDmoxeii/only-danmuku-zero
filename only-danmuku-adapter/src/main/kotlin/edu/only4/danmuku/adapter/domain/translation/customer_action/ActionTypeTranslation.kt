package edu.only4.danmuku.adapter.domain.translation.customer_action

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType
import org.springframework.stereotype.Component

@TranslationType(type = "customer_action_action_type_code_to_desc")
@Component
class ActionTypeTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val CUSTOMER_ACTION_ACTION_TYPE_CODE_TO_DESC = "customer_action_action_type_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return ActionType.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
