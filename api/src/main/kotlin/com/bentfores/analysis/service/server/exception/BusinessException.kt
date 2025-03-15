package com.adron.bot.manager.api.exception

class BusinessException(
    private var errorCode: ErrorCode,
    private var args: Map<String, Any?> = mapOf(),
) : RuntimeException() {

    constructor(errorCode: ErrorCode, pair: Pair<String, Any>): this(errorCode, mapOf(pair))

    fun getErrorCode(): String = errorCode.name

    fun getErrorMessage(): String = errorCode.message

    fun getArgs(): Map<String, Any?> = args

    override fun toString(): String {
        return """
            Code: ${getErrorCode()}
            Message: ${getErrorMessage()}
            Params: $args
        """.trimIndent()
    }
}