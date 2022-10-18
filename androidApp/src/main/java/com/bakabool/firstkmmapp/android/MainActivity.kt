package com.bakabool.firstkmmapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bakabool.firstkmmapp.android.databinding.ActivityMainBinding
import com.bakabool.firstkmmapp.shared.cache.DatabaseDriverFactory
import com.bakabool.firstkmmapp.shared.cache.KmmSDK
import com.bakabool.findtimeshared.ShareClass

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val kmmSDK = KmmSDK(DatabaseDriverFactory(this))
        val shareClass  = ShareClass()
        val listOfNotes = kmmSDK.getAllNotes().toMutableList()
        //kmmSDK.insertNote("Hello","KMM is fun")
        binding.textView.text = listOfNotes.first().title

//        setContent {
//            MyApplicationTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    val scope = rememberCoroutineScope()
//                    var text by remember { mutableStateOf("Loading") }
//
//                    /**
//                     * to allow a composable view to wait for an item to render, could be during
//                     * calculation or network operation in background, we use a remember lamda
//                     * and specify the mutable intial state. Once this data is loaded
//                     * via the LaunchedEffect the UI will render.
//                     */
//                    var listOfRockets by remember {
//                        mutableStateOf(listOf<String>())
//                    }
//                    LaunchedEffect(true) {
//                        scope.launch {
//                            text = try {
//                                Rockets().rocketGreeting()
//                            } catch (e: Exception) {
//                                e.localizedMessage ?: "error"
//                            }
//                            listOfRockets = try {
//                                val rockets = Rockets().fetchRockets()
//                                rockets.map { it.missionName }
//                            } catch (e:Exception) {
//                                println(e)
//                                emptyList()
//                            }
//                        }
//                    }
//                    Column() {
//                        /**
//                         * surface adds a shadow elevation on composed views wrapped in it
//                         */
//                        Surface(shape = MaterialTheme.shapes.medium, elevation = 2.dp) {
//                            Greeting(text)
//                        }
//                        ListOfRockets(listOfRockets)
//                    }
//                   // Greeting(Greeting().greeting())
//                   // Greeting("${listOfNotes.first().title}\n${listOfNotes.first().body}")
//                }
//            }
//        }
    }
}

@Composable
fun GreetingList(names: List<String>) {
    for (name in names) {
        Text("Hello $name")
    }
}

/**
 * A prevre annotation fun should not have params to pass.
 * Practive here is to create a Parent Preview fun and add tge composable view while passing
 * dummy data.
 */
@Preview(showSystemUi = true)
@Composable
fun PreviewGreeting() {
    Greeting("Hello Summer")
}


@Composable
fun Greeting(text: String) {
    val shape = CircleShape
    Text(
        text = text, fontSize = 16.sp, modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    )
}

@Composable
fun ListOfRockets(rockets: List<String>) {
    if(rockets.isEmpty()) {
        Greeting("Failed to load list")
    } else {
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            items(rockets.size) { index ->
                Text(text = rockets[index],modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .clickable {  }
                )

            }
//            items(rockets.size) { index ->
//                Text(text = rockets[index],modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(24.dp))
//            }
        }
    }

}

//@Preview
//@Composable
//fun DefaultPreview() {
//    MyApplicationTheme {
//        Greeting("Hello, Android!")
//    }
//}
