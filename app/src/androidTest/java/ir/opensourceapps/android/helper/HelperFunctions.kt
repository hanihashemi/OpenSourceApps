package ir.opensourceapps.android.helper

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


fun sleep(duration: Long) {
    try {
        Thread.sleep(duration)
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
}

fun stringFromFile(context: Context, filePath: String): String {
    val stream = context.resources.assets.open(filePath)

    val ret = convertStreamToString(stream)
    stream.close()
    return ret
}

private fun convertStreamToString(stream: InputStream): String {
    val reader = BufferedReader(InputStreamReader(stream))
    val sb = StringBuilder()

    var line: String? = reader.readLine()
    try {
        while (line != null) {
            sb.append(line).append('\n')
            line = reader.readLine()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        try {
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return sb.toString()
}