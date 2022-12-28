package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myModel by viewModels<MyModel>()
        setContent {
            initUI(myModel);
        }
    }
}

@Composable
fun initUI(viewModel: MyModel) {
    val textValue = remember {
        mutableStateOf("")//设置默认输入值为空
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(viewModel.inputStr.value)
        Spacer(modifier = Modifier.height(20.dp))
        TextField(value = textValue.value, modifier = Modifier.height(50.dp), onValueChange = {
            textValue.value = it
        })
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            viewModel.onInputChange(textValue.value)
        }, Modifier.width(120.dp)) {
            Text(text = "Display")
        }
    }
}

class MyModel : ViewModel() {
    var initData = "Hello "
    var inputStr = mutableStateOf(initData)
    fun onInputChange(inputContent: String) {
        inputStr.value = initData + inputContent
    }
}
