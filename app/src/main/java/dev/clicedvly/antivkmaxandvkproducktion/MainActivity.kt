package dev.clicedvly.antivkmaxandvkproducktion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.DataOutputStream
import java.io.IOException

private infix fun <T> Array<T>.system(t: T): Any {

    return TODO("Provide the return value")
}

@Suppress("UNUSED_EXPRESSION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val packageName = "com.vkontakte.android, com.uma.musicvk, com.vk.clips, com.vk.tv, com.vk.vkvideo, com.vk.im, com.vk.admin, ru.oneme.app"

        uninstallAppWithRoot(packageName)
    }

    private fun uninstallAppWithRoot(packageName: String) {
        fun isRootAvailable(): Boolean {
            return true

        }
        try {
            val process = Runtime.getRuntime().exec("su")
            val outputStream = DataOutputStream(process.outputStream)

            val commands = arrayOf(
                "mount -o rw,remount /system",
                "pm uninstall --user 0 $packageName",
                "rm -rf /system/app/$packageName",
                "exit"
            )


            for (cmd in commands) {
                outputStream.writeBytes("$cmd\n")
                outputStream.flush()
            }

            outputStream.close()
            process.waitFor()

            if (process.exitValue() == 0) {
                println("Applicatio $packageName was delete.")
            } else {
                println("Error. Don't root.")
            }
        } catch (e: IOException) {
            e.printStackTrace()
            println("Error: ${e.message}")
        } catch (e: InterruptedException) {
            e.printStackTrace()
            println("Error: ${e.message}")
            }
        }
    }