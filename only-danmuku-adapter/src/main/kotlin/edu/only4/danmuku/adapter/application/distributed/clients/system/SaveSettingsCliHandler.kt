package edu.only4.danmuku.adapter.application.distributed.clients.system

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.system.SaveSettingsCli
import org.springframework.stereotype.Service

@Service
class SaveSettingsCliHandler : RequestHandler<SaveSettingsCli.Request, SaveSettingsCli.Response> {

    override fun exec(request: SaveSettingsCli.Request): SaveSettingsCli.Response {
        return SaveSettingsCli.Response
    }
}
