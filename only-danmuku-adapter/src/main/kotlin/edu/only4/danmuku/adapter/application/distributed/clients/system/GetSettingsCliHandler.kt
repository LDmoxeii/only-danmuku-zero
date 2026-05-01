package edu.only4.danmuku.adapter.application.distributed.clients.system

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.system.GetSettingsCli
import org.springframework.stereotype.Service

@Service
class GetSettingsCliHandler : RequestHandler<GetSettingsCli.Request, GetSettingsCli.Response> {

    override fun exec(request: GetSettingsCli.Request): GetSettingsCli.Response {
        return GetSettingsCli.Response(
            registerCoinCount = TODO("set registerCoinCount"),
            postVideoCoinCount = TODO("set postVideoCoinCount"),
            videoSize = TODO("set videoSize"),
            videoPCount = TODO("set videoPCount"),
            videoCount = TODO("set videoCount"),
            commentCount = TODO("set commentCount"),
            danmukuCount = TODO("set danmukuCount"),
            renameNicknameCoinCost = TODO("set renameNicknameCoinCost")
        )
    }
}
