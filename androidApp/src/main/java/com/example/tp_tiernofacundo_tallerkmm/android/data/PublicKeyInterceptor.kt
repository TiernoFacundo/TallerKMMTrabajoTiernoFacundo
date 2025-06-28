package com.example.tp_tiernofacundo_tallerkmm.android.data

import com.example.tp_tiernofacundo_tallerkmm.data.PUBLIC_KEY
import okhttp3.Interceptor
import okhttp3.Response

class PublicKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url

        val newUrl = url.newBuilder()
            .addQueryParameter("apikey", "live_ReweqgIFgkIcIHYYfgGItyZcrXXG4VglvFAZD3lAkSh9Gg4FzIaKXWBGTF9VTuGT")
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }
}