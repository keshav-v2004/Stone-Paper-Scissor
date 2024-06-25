package com.example.stonepaperscissor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stonepaperscissor.ui.theme.StonePaperScissorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StonePaperScissorTheme {
                MainLayout()
            }
        }
    }
}

@Composable
fun MainLayout(modifier: Modifier=Modifier){


    //scores
    var you by remember {
        mutableStateOf(0)
    }

    var ties by remember {
        mutableStateOf(0)
    }

    var opponent by remember {
        mutableStateOf(0)
    }


    var leftimageid_user by remember {
        mutableStateOf<Int?>(null)
    }

    var rightimageid_comp by remember {
        mutableStateOf<Int?>(null)
    }

    var final_satement by remember {
        mutableStateOf<String?>(null)
    }



    val leftimgid=when(leftimageid_user) {
        0->{painterResource(id = R.drawable.rock)}

        1->{painterResource(id = R.drawable.paper)}

        2->{painterResource(id = R.drawable.scissors)}

        else->{
            painterResource(id = R.drawable.versus)}
    }
    val rightimgid=when(rightimageid_comp) {

        0->painterResource(id = R.drawable.rock)

        1->painterResource(id = R.drawable.paper)

        2->painterResource(id = R.drawable.scissors)

        else->{
            painterResource(id = R.drawable.versus)}
    }

    fun who_win(l:Int,r:Int){
        if(l==r){
            ties+=1
            final_satement="its a tie"
        }
        else if(l>r){
            if (l==1){
                you+=1
                final_satement="you won"
            }
            else{
                opponent+=1
                final_satement="you lost"
            }
        }
        else{
            if(r==1){
                opponent+=1
                final_satement="you lost"
            }
            else{
                you+=1
                final_satement="you won"

            }


        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .border(width = 2.dp, color = Color.DarkGray, shape = RectangleShape)
    ) {

        Spacer(modifier = modifier.height(45.dp))


        Text(
            text = "STONE   PAPER   SCISSORS",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier=modifier
                .fillMaxWidth()
        )


        Spacer(modifier = modifier.height(50.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()

        ) {
            Text(
                text = "YOU",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = modifier
            )

            Text(
                text = "TIES",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = modifier
            )
            Text(
                text = "OPPONENT",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = modifier
            )
        }


        //this row will be for displaying scores
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()

        ) {
            Text(
                text = "$you",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = modifier
            )

            Text(
                text = "$ties",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = modifier
            )
            Text(
                text = "$opponent",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = modifier
            )
        }
        Spacer(modifier = modifier.height(50.dp))

        ImageRow(leftimg = leftimgid, rightimg = rightimgid)


        Spacer(modifier = modifier.height(150.dp))

        Text(
            text = "SELECT YOUR WEAPON",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold

        )
        Spacer(modifier = modifier.height(50.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {
            WeaponButton(
                imageid = painterResource(id = R.drawable.rock),
                onclick = {
                    leftimageid_user=0
                    rightimageid_comp=(0..2).random()
                    who_win(leftimageid_user!!, rightimageid_comp!!)

                }
            )

            WeaponButton(imageid = painterResource(id = R.drawable.paper),
                onclick = {

                    leftimageid_user=1
                    rightimageid_comp=(0..2).random()
                    who_win(leftimageid_user!!, rightimageid_comp!!)
                }
            )

            WeaponButton(imageid = painterResource(id = R.drawable.scissors),
                onclick = {

                    leftimageid_user=2
                    rightimageid_comp=(0..2).random()
                    who_win(leftimageid_user!!, rightimageid_comp!!)
                }
            )
            
        }

        Spacer(modifier = modifier.height(50.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = modifier.height(70.dp))

            Text(
                text = when (final_satement) {
                    null -> "welcome"
                    else -> "$final_satement"
                },
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold


            )

            reset(
                {
                    leftimageid_user=null
                    rightimageid_comp=null
                    you=0
                    ties=0
                    opponent=0
                    final_satement=null

                }
            )
        }



    }
}



@Composable
fun ImageRow(modifier: Modifier=Modifier,leftimg:Painter,rightimg:Painter){
    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly
    ) {


        val versusimg= painterResource(id = R.drawable.versus)


        Image(
            painter = leftimg,
            contentDescription = null,
            modifier = modifier
                .size(100.dp)

        )
        Image(painter = versusimg,
            contentDescription = null,
            modifier = modifier
                .size(100.dp))

        Image(
            painter = rightimg,
            contentDescription = null,
            modifier = modifier
                .size(100.dp)
        )

    }
}


@Composable
fun WeaponButton(
    imageid:Painter,
    onclick:()->Unit,
    modifier: Modifier=Modifier,

){
    Image(
        painter = imageid ,
        contentDescription = null,
        modifier= modifier
            .clickable(
                enabled = true,
                onClick = onclick
            )
            .size(100.dp)
    )
}

@Composable

fun reset(
    onclick: () -> Unit,
    modifier: Modifier=Modifier
){
    Button(
        onClick = onclick
    ) {
        Text(
            text = "RESET"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    StonePaperScissorTheme {
        MainLayout()
    }
}