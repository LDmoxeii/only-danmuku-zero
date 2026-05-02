package edu.only4.danmuku.adapter.portal.api.payload.file

import java.util.UUID

object DeleteUploadSession {
    data class Request(
        val uploadId: UUID,
    )
}

