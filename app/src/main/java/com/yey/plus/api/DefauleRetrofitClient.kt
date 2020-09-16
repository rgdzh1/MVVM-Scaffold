import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.yey.plus.VCashApp
import okhttp3.OkHttpClient

object DefauleRetrofitClient : BRetrofitClient() {
    val mBApi by lazy {
        getApi(VApi::class.java, VApi.B_URL)
    }

    override fun myConfig(mBuilder: OkHttpClient.Builder) {
        // 使用cookie
        mBuilder.cookieJar(
            PersistentCookieJar(
                SetCookieCache(),
                SharedPrefsCookiePersistor(VCashApp.CONTEXT)
            )
        )
    }
}