package com.flatrocktech.demo.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flatrocktech.demo.GithubRepositoryModel
import com.flatrocktech.demo.GithubSDK
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()
            val repos = remember { mutableStateOf<List<GithubRepositoryModel>>(emptyList()) }

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(true) {
                        scope.launch {
                            repos.value = try {
                                GithubSDK().getRepos()
                            } catch (e: Exception) {
                                emptyList()
                            }
                        }
                    }
                    LazyColumn {
                        item {
                            Text(
                                text = "Github Repositories",
                                style = TextStyle(fontSize = 30.sp),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                            )
                        }
                        items(repos.value.size) { index ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(all = 10.dp)
                            ) {
                                Text(
                                    "Repo name: ${repos.value[index].name}",
                                    modifier = Modifier.padding(all = 5.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

