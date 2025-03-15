package com.adron.bot.manager.api.exception

enum class ErrorCode(
  val status: Int,
  val message: String,
) {
  BMNG_00(400, "Validation Error"),
  BMNG_01(400, "Bot not found"),
  BMNG_02(400, "None of query params were passed for searching"),
  BMNG_03(400, "Couldn't close bot. Incorrect status"),
  BMNG_04(400, "Order not found"),
  BMNG_05(400, "Bot creation failure. Limit of max bots was reached"),
  BMNG_06(400, "Invalid or prohibited sort key"),
  BMNG_07(400, "Bad request. Expected at least one of these params: profileId, accountId"),
  BMNG_08(400, "Account not found by id"),
  BMNG_09(400, "Ticker not found or service is unavailable"),
  BMNG_10(400, "Notional order amount too small. Orders do not meet minimum order value"),
  BMNG_11(400, "Can't update order. Incorrect order type"),

  AGS_06(400, "Leverage can not be different from other bots' leverage on the same ticker");

  companion object {
    const val MIN_NOTIONAL = "minNotional"
    const val BOT_ID = "botId"
    const val STATUS = "status"
    const val PROFILE_ID = "profileId"
    const val MAX_PROFILE_BOTS = "maxProfileBots"
    const val ORDER_ID = "orderId"
    const val ORDER_TYPE = "orderType"
    const val SORT_KEYS = "sortKeys"
  }
}