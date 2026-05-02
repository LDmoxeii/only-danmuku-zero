package edu.only4.danmuku.application.commands.customer_profile

import java.util.UUID

import edu.only4.danmuku.domain.aggregates.video_quality_policy.*

import edu.only4.danmuku.domain.aggregates.video_post_processing.*

import edu.only4.danmuku.domain.aggregates.video_post.*

import edu.only4.danmuku.domain.aggregates.video_play_history.*

import edu.only4.danmuku.domain.aggregates.video_hls_key_token.*

import edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.*

import edu.only4.danmuku.domain.aggregates.video_danmuku.*

import edu.only4.danmuku.domain.aggregates.video_comment.*

import edu.only4.danmuku.domain.aggregates.video.*

import edu.only4.danmuku.domain.aggregates.user.*

import edu.only4.danmuku.domain.aggregates.statistics.*

import edu.only4.danmuku.domain.aggregates.video_file_upload_session.*

import edu.only4.danmuku.domain.aggregates.customer_video_series.*

import edu.only4.danmuku.domain.aggregates.customer_profile.*

import edu.only4.danmuku.domain.aggregates.customer_message.*

import edu.only4.danmuku.domain.aggregates.category.*

import com.only.engine.error.CommonErrors
import com.only.engine.exception.AppException
import com.only.engine.exception.BusinessException
import com.only.engine.exception.DependencyException
import com.only.engine.exception.RequestException
import com.only.engine.exception.SystemException
import edu.only4.danmuku.domain.shared.error.DanmukuBusinessErrors
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.application.validators.NicknameChangeAllowed
import edu.only4.danmuku.application.validators.UniqueUserNickname
import edu.only4.danmuku.domain._share.meta.customer_profile.SCustomerProfile
import edu.only4.danmuku.domain.aggregates.customer_profile.enums.SexType
import edu.only4.danmuku.domain.aggregates.customer_profile.enums.ThemeType
import org.springframework.stereotype.Service

/**
 * 更新用户信息
 */
object UpdateCustomerProfileCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val profile = Mediator.repositories.findFirst(
                SCustomerProfile.predicate { it.userId eq request.customerId },
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "用户资料不存在：${request.customerId}")

            profile.updateProfileInfo(
                nickName = request.nickName,
                avatar = request.avatar,
                sex = request.sex?.let { SexType.valueOfOrNull(it) },
                birthday = request.birthday,
                school = request.school,
                personIntroduction = request.personIntroduction,
                noticeInfo = request.noticeInfo,
                theme = request.theme?.let { ThemeType.valueOfOrNull(it) }
            )

            Mediator.uow.save()
        
            return Response
        }
    }

    @UniqueUserNickname(userIdField = "customerId", nicknameField = "nickName")
    @NicknameChangeAllowed(userIdField = "customerId", nicknameField = "nickName")
    data class Request(
        /** 用户ID */
        val customerId: UUID,
        /** 昵称 */
        val nickName: String? = null,
        /** 头像 */
        val avatar: String? = null,
        /** 性别值，对应 SexType.value */
        val sex: Int? = null,
        /** 生日 */
        val birthday: String? = null,
        /** 学校 */
        val school: String? = null,
        /** 个人简介 */
        val personIntroduction: String? = null,
        /** 空间公告 */
        val noticeInfo: String? = null,
        /** 主题值，对应 ThemeType.value */
        val theme: Int? = null,
    ) : RequestParam<Response>

    data object Response
}

