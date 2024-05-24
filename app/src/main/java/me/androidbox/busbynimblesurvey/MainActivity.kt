package me.androidbox.busbynimblesurvey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.launch
import me.androidbox.busbynimblesurvey.ui.theme.BusbyNimbleSurveyTheme
import me.androidbox.domain.authorization.usecases.ResetPasswordUseCase
import me.androidbox.presentation.authentication.login.LoginScreenRoot
import me.androidbox.presentation.components.GradientBackground
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

   //     val registerUseCase by inject<RegisterUseCase>()
    //    val loginUseCase by inject<LoginUseCase>()*/
        val resetPasswordUseCase by inject<ResetPasswordUseCase>()

        println(BuildConfig.CLIENT_KEY)
        println(BuildConfig.CLIENT_SECRET)

        setContent {
            BusbyNimbleSurveyTheme {
                val scope = rememberCoroutineScope()

                GradientBackground {
                    Navigator(screen = LoginScreenRoot)
                }

                LaunchedEffect(key1 = true) {
                    scope.launch {
                     //   val result = registerUseCase.execute(RegisterUserModel("", "", "", ""))

                     //   val result = loginUseCase.execute(LoginRequestModel("", "", "", "", ""))
                        val result = resetPasswordUseCase.execute()
                        Timber.d("API Response %s", result)
                    }
                }
            }
        }
    }
}
