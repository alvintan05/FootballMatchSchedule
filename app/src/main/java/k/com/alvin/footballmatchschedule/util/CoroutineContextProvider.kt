package k.com.alvin.footballmatchschedule.util

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Alvin Tandiardi on 12/11/2018.
 */
open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { UI }
}