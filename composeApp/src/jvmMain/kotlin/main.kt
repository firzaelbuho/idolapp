import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension
import com.langitbiru.idol.App
import com.langitbiru.idol.di.homeModule
import com.langitbiru.idol.di.initKoin
import com.langitbiru.idol.logger.initLogger
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.intercept.bitmapMemoryCacheConfig
import com.seiko.imageloader.intercept.imageMemoryCacheConfig
import com.seiko.imageloader.intercept.painterMemoryCacheConfig
import okio.Path.Companion.toOkioPath
import java.io.File

fun main() {

    application {
        initKoin()
        Window(
            title = "Idol App",
            state = rememberWindowState(width = 800.dp, height = 600.dp),
            onCloseRequest = ::exitApplication,
        ) {
            window.minimumSize = Dimension(350, 600)
            CompositionLocalProvider(
                LocalImageLoader provides remember { generateImageLoader() },
            ) {
                App()
            }
        }
    }
}

fun generateImageLoader(): ImageLoader {
    return ImageLoader {
        components {
            setupDefaultComponents()
        }
        interceptor {
            // cache 32MB bitmap
            bitmapMemoryCacheConfig {
                maxSize(32 * 1024 * 1024) // 32MB
            }
            // cache 50 image
            imageMemoryCacheConfig {
                maxSize(50)
            }
            // cache 50 painter
            painterMemoryCacheConfig {
                maxSize(50)
            }
            diskCacheConfig {
                directory(getCacheDir().toOkioPath().resolve("image_cache"))
                maxSizeBytes(512L * 1024 * 1024) // 512MB
            }
        }
    }
}


enum class MyOperatingSystem {
    Windows, Linux, MacOS, Unknown
}

private val currentOperatingSystem: MyOperatingSystem
    get() {
        val operSys = System.getProperty("os.name").lowercase()
        return if (operSys.contains("win")) {
            MyOperatingSystem.Windows
        } else if (operSys.contains("nix") || operSys.contains("nux") ||
            operSys.contains("aix")
        ) {
            MyOperatingSystem.Linux
        } else if (operSys.contains("mac")) {
            MyOperatingSystem.MacOS
        } else {
            MyOperatingSystem.Unknown
        }
    }

// about currentOperatingSystem, see app
private fun getCacheDir() = when (currentOperatingSystem) {
    MyOperatingSystem.Windows -> File(System.getenv("AppData"), "$ApplicationName/cache")
    MyOperatingSystem.Linux -> File(System.getProperty("user.home"), ".cache/$ApplicationName")
    MyOperatingSystem.MacOS -> File(System.getProperty("user.home"), "Library/Caches/$ApplicationName")
    else -> throw IllegalStateException("Unsupported operating system")
}



private const val ApplicationName = "Compose ImageLoader"

