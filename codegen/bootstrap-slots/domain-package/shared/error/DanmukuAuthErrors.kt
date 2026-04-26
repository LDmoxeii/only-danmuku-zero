package {{ basePackage }}.domain.shared.error

import com.only.engine.error.BusinessErrorCode

object DanmukuAuthErrors {
    object CAPTCHA_INVALID : BusinessErrorCode(40240, "CAPTCHA_INVALID", "验证码错误")
}
