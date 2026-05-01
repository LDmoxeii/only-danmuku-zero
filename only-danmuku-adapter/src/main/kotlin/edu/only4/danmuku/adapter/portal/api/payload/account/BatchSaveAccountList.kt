
package edu.only4.danmuku.adapter.portal.api.payload.account

object BatchSaveAccountList {

    data class Request(
        val globalId: String = "0",
        val account: AccountInfo
    ) {
        data class AccountInfo(
            val accountNumber: String,
            val accountName: String,
            val bank: Bank,
            val currency: String
        )
        data class Bank(
            val code: String
        )
    }

    data class Response(
        val result: Boolean
    )

}
