import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.langitbiru.idol.App
import com.langitbiru.idol.di.initKoin
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.intercept.bitmapMemoryCacheConfig
import org.jetbrains.skiko.wasm.onWasmReady
import kotlinx.browser.document
import okio.FileSystem
import okio.fakefilesystem.FakeFileSystem

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()
    onWasmReady {
        val body = document.body ?: return@onWasmReady
        ComposeViewport(body) {
            CompositionLocalProvider(
                LocalImageLoader provides remember { generateImageLoader() },
            ) {
                SelectionContainer {
                    App()
                }
            }
        }
    }
}


private fun generateImageLoader(): ImageLoader {
    return ImageLoader {
//        commonConfig()
        components {
            setupDefaultComponents()
        }
        interceptor {
            bitmapMemoryCacheConfig {
                maxSize(32 * 1024 * 1024) // 32MB
            }
            diskCacheConfig(FakeFileSystem().apply { emulateUnix() }) {
                directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY)
                maxSizeBytes(256L * 1024 * 1024) // 256MB
            }
        }
    }
}

private fun generateImageLoader2(): ImageLoader {
    return ImageLoader {
        components {
            setupDefaultComponents()
        }
        interceptor {
            bitmapMemoryCacheConfig {
                maxSize(32 * 1024 * 1024) // 32MB
            }
        }
    }
}
