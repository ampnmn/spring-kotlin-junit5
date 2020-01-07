package com.ampnmn.springkotlinjunit5.model

import org.springframework.util.StreamUtils
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.io.File
import java.io.OutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class ZipStreamingResponseBody(private vararg var files: File) : StreamingResponseBody {
    override fun writeTo(outputStream: OutputStream) {
        ZipOutputStream(outputStream).use { zipOutputStream ->
            files.forEach { file ->
                file.inputStream().use { inputStream ->
                    zipOutputStream.putNextEntry(ZipEntry(file.name))
                    StreamUtils.copy(inputStream, zipOutputStream)
                }
                zipOutputStream.closeEntry()
            }
        }
    }
}
