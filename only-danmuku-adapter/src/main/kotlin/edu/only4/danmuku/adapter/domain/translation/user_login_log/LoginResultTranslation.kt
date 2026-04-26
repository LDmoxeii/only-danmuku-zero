package edu.only4.danmuku.adapter.domain.translation.user_login_log

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.user_login_log.enums.LoginResult
import org.springframework.stereotype.Component

@TranslationType(type = "user_login_log_login_result_code_to_desc")
@Component
class LoginResultTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val USER_LOGIN_LOG_LOGIN_RESULT_CODE_TO_DESC = "user_login_log_login_result_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return LoginResult.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
