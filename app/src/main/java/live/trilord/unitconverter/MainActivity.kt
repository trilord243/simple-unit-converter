package live.trilord.unitconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import live.trilord.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
              UnitConverter()
            }
        }
    }
}


@Composable
fun UnitConverter(){
    var inputValue by remember{ mutableStateOf("") }
    var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Metros") }
    var outputUnit by remember { mutableStateOf("Metros") }
    var isExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val converSionFactor = remember {
        mutableStateOf(1.0)
    }
    val oConverSionFactor = remember {
        mutableStateOf(1.0)
    }

val customTextStyle=TextStyle(

    fontFamily = FontFamily.SansSerif,
    fontSize = 20.sp,
    color = Color.Red
)
    fun convertUnits(){
        val inputValueDouble=inputValue.toDoubleOrNull() ?:  0.0
        val result = (inputValueDouble  *  converSionFactor.value*100/oConverSionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }

    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment=Alignment.CenterHorizontally


    ) {
        //Elementos de UI uno de abajo del otro
        Text("Conversion de unidades"  , style = MaterialTheme.typography.headlineLarge )
        Spacer(modifier = Modifier.padding(16.dp))
       OutlinedTextField(value = inputValue,
           onValueChange ={
           inputValue = it
               convertUnits()
           },
           label={Text("Introduce el valor a convertir")}
           )
        Spacer(modifier = Modifier.padding(16.dp))
        Row{
            //Estos elementos estaran al lado uno del otro

        Box{
            Button(onClick = { isExpanded= true }) {
                Text(text = inputUnit)
               Icon(Icons.Default.ArrowDropDown, contentDescription = "")

            }
            DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded= false}) {
                DropdownMenuItem(text = { Text(text = "Centimetros ")}, onClick = {
                    inputUnit="Centimetros"
                    isExpanded=false
                    converSionFactor.value = 0.01
                    convertUnits()

                })
                DropdownMenuItem(text = { Text(text = "Metros ")}, onClick = {   inputUnit="Metros"
                    isExpanded=false
                    converSionFactor.value = 1.0
                    convertUnits()})
                DropdownMenuItem(text = { Text(text = "Pies ")}, onClick = {   inputUnit="Pies"
                    isExpanded=false
                    converSionFactor.value = 0.3048
                    convertUnits()})
                DropdownMenuItem(text = { Text(text = "Milimetros ")}, onClick = {   inputUnit="Milimetros"
                    isExpanded=false
                    converSionFactor.value = 0.001
                    convertUnits()})

            }
            Spacer(modifier =Modifier.padding(5.dp))
        }
            Spacer(modifier = Modifier.width(4.dp))
            Box{
                Button(onClick = { oExpanded= true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }

                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "Centimetros ")}, onClick = { outputUnit="Centimetros"
                        oExpanded=false
                        oConverSionFactor.value = 0.01
                        convertUnits()

                    })
                    DropdownMenuItem(text = { Text(text = "Metros ")}, onClick = { outputUnit="Metros"
                        oExpanded=false
                        oConverSionFactor.value = 1.0
                        convertUnits()

                    })
                    DropdownMenuItem(text = { Text(text = "Pies ")}, onClick = {
                        outputUnit="Pies"
                        oExpanded=false
                        oConverSionFactor.value = 0.3048
                        convertUnits()

                    })
                    DropdownMenuItem(text = { Text(text = "Milimetros ")}, onClick = {
                        outputUnit="Milimetros"
                        oExpanded=false
                        oConverSionFactor.value = 0.001
                        convertUnits() })

                }

            }



        }
        Spacer(modifier = Modifier.padding(16.dp))
        Text("Resultado: $outputValue $outputUnit" ,style= MaterialTheme.typography.headlineMedium   )

    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}

