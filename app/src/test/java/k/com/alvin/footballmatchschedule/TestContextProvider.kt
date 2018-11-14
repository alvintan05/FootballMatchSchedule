package k.com.alvin.footballmatchschedule

import k.com.alvin.footballmatchschedule.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Alvin Tandiardi on 13/11/2018.
 */
class TestContextProvider: CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}