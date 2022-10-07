package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationApp()
        }
    }
}


@Composable
fun AffirmationApp(){
    AffirmationsTheme{
        AffirmationList(affirmationList = Datasource().loadAffirmations())
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation,modifier: Modifier=Modifier){
    Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {


        Column {
            Image(
                painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(
                    id = affirmation.stringResourceId
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
private fun AffirmationList(affirmationList: List<Affirmation>,modifier: Modifier=Modifier){
//    Column {
//        affirmationList.forEach{
//            affirmation ->
//            AffirmationCard(affirmation = affirmation)
//        }
//    }
    LazyColumn{
        items(affirmationList){
            affirmation->
            AffirmationCard(affirmation = affirmation)
        }
    }
}

//@Preview
@Composable
fun AffirmationCardPreview(){
    AffirmationsTheme{
        AffirmationCard(affirmation = Affirmation(
            stringResourceId = R.string.affirmation1,
            imageResourceId = R.drawable.image1
        ))
    }
}