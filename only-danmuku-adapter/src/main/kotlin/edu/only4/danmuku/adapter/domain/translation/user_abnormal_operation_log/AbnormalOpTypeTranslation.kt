package edu.only4.danmuku.adapter.domain.translation.user_abnormal_operation_log

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log.enums.AbnormalOpType
import org.springframework.stereotype.Component

@TranslationType(type = "user_abnormal_operation_log_abnormal_op_type_code_to_desc")
@Component
class AbnormalOpTypeTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val USER_ABNORMAL_OPERATION_LOG_ABNORMAL_OP_TYPE_CODE_TO_DESC = "user_abnormal_operation_log_abnormal_op_type_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return AbnormalOpType.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
