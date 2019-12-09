package com.fsck.k9m.autocrypt


import com.fsck.k9m.K9
import com.fsck.k9m.mail.Address
import com.fsck.k9m.mail.Flag
import com.fsck.k9m.mail.Message
import com.fsck.k9m.mail.MessagingException
import com.fsck.k9m.mail.internet.MimeBodyPart
import com.fsck.k9m.mail.internet.MimeHeader
import com.fsck.k9m.mail.internet.MimeMessage
import com.fsck.k9m.mail.internet.MimeMessageHelper
import com.fsck.k9m.mail.internet.MimeMultipart
import com.fsck.k9m.mail.internet.TextBody
import com.fsck.k9m.mailstore.BinaryMemoryBody
import java.util.Date


class AutocryptTransferMessageCreator(private val stringProvider: AutocryptStringProvider) {
    fun createAutocryptTransferMessage(data: ByteArray, address: Address): Message {
        try {
            val subjectText = stringProvider.transferMessageSubject()
            val messageText = stringProvider.transferMessageBody()

            val textBodyPart = MimeBodyPart(TextBody(messageText))
            val dataBodyPart = MimeBodyPart(BinaryMemoryBody(data, "7bit"))
            dataBodyPart.setHeader(MimeHeader.HEADER_CONTENT_TYPE, "application/autocrypt-setup")
            dataBodyPart.setHeader(MimeHeader.HEADER_CONTENT_DISPOSITION, "attachment; filename=\"autocrypt-setup-message\"")

            val messageBody = MimeMultipart.newInstance()
            messageBody.addBodyPart(textBodyPart)
            messageBody.addBodyPart(dataBodyPart)

            val message = MimeMessage()
            MimeMessageHelper.setBody(message, messageBody)

            val nowDate = Date()

            message.setFlag(Flag.X_DOWNLOADED_FULL, true)
            message.subject = subjectText
            message.setHeader("Autocrypt-Setup-Message", "v1")
            message.internalDate = nowDate
            message.addSentDate(nowDate, K9.isHideTimeZone)
            message.setFrom(address)
            message.setHeader("To", address.toEncodedString())

            return message
        } catch (e: MessagingException) {
            throw AssertionError(e)
        }
    }
}
