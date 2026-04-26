package edu.only4.danmuku.adapter.domain.translation.shared

import com.only.engine.translation.annotation.TranslationType
import com.only.engine.translation.core.BatchTranslationInterface
import com.only.engine.translation.core.TranslationInterface
import edu.only4.danmuku.domain.aggregates.video_post.enums.PostType
import org.springframework.stereotype.Component

@TranslationType(type = "post_type_code_to_desc")
@Component
class PostTypeTranslation : TranslationInterface<String>, BatchTranslationInterface<String> {

    companion object {
        const val POST_TYPE_CODE_TO_DESC = "post_type_code_to_desc"
    }

    override fun translation(key: Any, other: String): String? {
        val code = when (key) {
            is Number -> key.toInt()
            is String -> key.toIntOrNull()
            else -> null
        } ?: return null
        return PostType.valueOfOrNull(code)?.description
    }

    override fun translationBatch(keys: Collection<Any>, other: String): Map<Any, String?> =
        keys.associateWith { translation(it, other) }
}
