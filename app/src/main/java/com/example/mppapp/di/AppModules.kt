//package com.example.mppapp.di
//
//import android.content.SharedPreferences
//import android.preference.PreferenceManager
//import com.example.mppapp.MainActivity
//import data.SpecializationsRepository
//import org.koin.android.ext.koin.androidContext
//import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.dsl.module
//import java.lang.reflect.Array.get
//
//val appModule = module {
//    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
//    single { AppExecutors() }
////    single { Gson() }
//}
//
//val authModule = module {
//    // single instance of HelloRepository
//    single<SpecializationsRepository>()
//    viewModel<MainViewModel>()
//}
//
//val mainModule = module {
//    scope("main") { (params: MainActivity) -> NavigationController(params) }
//    scope("main") { get<Gson>().fromJson(get<SharedPreferences>().getString(User.PREF_USER, ""), User::class.java) }
//    single<SpecializationsRepository>()
//}
//
//val getFactory = JsonAndXmlConverters.QualifiedTypeConverterFactory(
//    GsonConverterFactory.create(),
//    SimpleXmlConverterFactory.create()
//)
//
//val networkingModule = module {
//    single {
//        val clientBuilder = OkHttpClient.Builder()
//        val loggingInterceptor = HttpLoggingInterceptor()
//        if (BuildConfig.DEBUG)
//            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        clientBuilder
//            .addInterceptor(loggingInterceptor)
//            .addNetworkInterceptor(StethoInterceptor())
//            .connectTimeout(20, TimeUnit.SECONDS)
//            .writeTimeout(120, TimeUnit.SECONDS)
//            .readTimeout(120, TimeUnit.SECONDS)
//        clientBuilder.build()
//    }
//
//    single {
//        Retrofit.Builder()
//            .baseUrl(UrlProvider.prodUrl)
//            .client(get())
//            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(getFactory)
//            .addCallAdapterFactory(LiveDataCallAdapterFactory())
//            .build()
//            .create(ApiService::class.java)
//    }
//}