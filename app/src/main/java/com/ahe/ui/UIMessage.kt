package com.ahe.ui

data class UIMessage(
    val message: String,
    val uiMessageType: UIMessageType
)

sealed class UIMessageType{

    class Toast: UIMessageType()

    class Dialog: UIMessageType()

    class AreYouSureDialog(
        val callback: AreYouSureCallback
    ): UIMessageType()

    class UserTypeDialog(
        val callback: UserTypeCallback
    ): UIMessageType()

    class None: UIMessageType()
}