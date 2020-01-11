package com.ampnmn.springkotlinjunit5.model

import org.springframework.util.StreamUtils
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@Suppress("unused")
class ZippedFileStreamingResponseBodyWriter(private vararg var files: File) : StreamingResponseBody {
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

class SingleFileStreamingResponseBodyWriter(private val lines: List<String>) : StreamingResponseBody {
    override fun writeTo(outputStream: OutputStream) {
        OutputStreamWriter(outputStream, "UTF-8").use { writer ->
            BufferedWriter(writer).use { bufferedWriter ->
                lines.forEach { line ->
                    bufferedWriter.write(line)
                    bufferedWriter.newLine()
                }
            }
        }
    }
}
