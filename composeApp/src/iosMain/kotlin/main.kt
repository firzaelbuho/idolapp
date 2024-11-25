import androidx.compose.ui.window.ComposeUIViewController
import com.langitbiru.idol.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
