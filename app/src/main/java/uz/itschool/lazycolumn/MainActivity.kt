package uz.itschool.lazycolumn

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.itschool.lazycolumn.ui.theme.LazyColumnTheme

class MainActivity : ComponentActivity() {

    var itemsses = mutableListOf<Sneakers>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemsses.add(Sneakers("Nike ",55,25,R.drawable.img,22,4.5))
        itemsses.add(Sneakers("Adidas1 ",52,25,R.drawable.img,22,4.5))
        itemsses.add(Sneakers("Nike2 ",234,25,R.drawable.img,22,4.5))
        itemsses.add(Sneakers("Nike3 ",544,25,R.drawable.img,22,4.5))
        itemsses.add(Sneakers("ABIBAS ",57,25,R.drawable.img,22,4.5))
        itemsses.add(Sneakers("Nike 5",87,25,R.drawable.img,22,4.5))
        itemsses.add(Sneakers("Nike 6",35,25,R.drawable.img,22,4.5))
        setContent {
            LazyColumnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    myView(list = itemsses)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myView(list:MutableList<Sneakers>){
    var itemList by remember {
        mutableStateOf(list)
    }

    var text by remember { mutableStateOf("") }
    Column (modifier = Modifier.fillMaxSize()){
        OutlinedTextField(value = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            onValueChange = {
                text=it
                itemList=changeList(it,list)

            },
            label = { Text("Search")}
        )
        LazyColumn(modifier = Modifier.padding(horizontal = 10.dp)){
            items(itemList){
                ProductItem(it)
            }
        }
    }


}
fun changeList(text:String,arrayList: MutableList<Sneakers>):MutableList<Sneakers>{
    var list= mutableListOf<Sneakers>()
    list.addAll(arrayList)
    Log.d("TAG","$text ${list.size}")
    for(i in 0 until arrayList.size){
        if (!arrayList[i].name.lowercase().contains(text.lowercase())){
            list.remove(arrayList[i])
        }
    }

    return list
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(sneakers:Sneakers) {
    val context=LocalContext.current
    Card(onClick = {
                   Toast.makeText(context,"your chose ${sneakers.name}",Toast.LENGTH_SHORT).show()
    },modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 20.dp),
        shape = RoundedCornerShape(1.dp)
    ) {
        Row(){
            Image(painter = painterResource(id = sneakers.image),modifier= Modifier
                    .size(100.dp)
                    , contentScale = ContentScale.Crop, contentDescription ="image" )
        Column (modifier = Modifier.padding(start = 20.dp)){

        Text(text = "${sneakers.name}", fontSize =16.sp, modifier = Modifier.padding(top = 5.dp))

        Row (modifier= Modifier
            .padding(top = 5.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            Text(text = "$${sneakers.price}", fontWeight = FontWeight.ExtraBold, fontSize = 25.sp)
            Text(text = "${sneakers.discount}"+"%  discount",modifier=Modifier.padding(horizontal = 10.dp), fontSize = 20.sp, color = Color.Red)
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)){
            Image(painter = painterResource(id = R.drawable.baseline_stars_24), contentDescription = "star rating")
            Text(text = "${sneakers.rating}")
            Text(text = "(${sneakers.coments})", modifier = Modifier.padding(start = 5.dp))
        }
        }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    var itemsses:ArrayList<Sneakers> = ArrayList<Sneakers>()

    itemsses.add(Sneakers("Nike ",55,25,R.drawable.img,22,4.5))
    itemsses.add(Sneakers("Adidas1 ",52,25,R.drawable.img,22,4.5))
    itemsses.add(Sneakers("Nike2 ",234,25,R.drawable.img,22,4.5))
    itemsses.add(Sneakers("Nike3 ",544,25,R.drawable.img,22,4.5))
    itemsses.add(Sneakers("ABIBAS ",57,25,R.drawable.img,22,4.5))
    itemsses.add(Sneakers("Nike 5",87,25,R.drawable.img,22,4.5))
    itemsses.add(Sneakers("Nike 6",35,25,R.drawable.img,22,4.5))
    LazyColumnTheme {
        myView(list = itemsses)
    }
}