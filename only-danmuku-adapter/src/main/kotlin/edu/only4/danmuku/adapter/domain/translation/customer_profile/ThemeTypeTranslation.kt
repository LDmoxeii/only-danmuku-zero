package edu.only4.danmuku.adapter.domain.translation.customer_profile

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.customer_profile.enums.ThemeType
import org.springframework.stereotype.Component

@TranslationType(type = "customer_profile_theme_type_code_to_desc")
@Component
class ThemeTypeTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val CUSTOMER_PROFILE_THEME_TYPE_CODE_TO_DESC = "customer_profile_theme_type_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return ThemeType.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
