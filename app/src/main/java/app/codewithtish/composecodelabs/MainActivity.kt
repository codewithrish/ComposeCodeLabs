package app.codewithtish.composecodelabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.codewithtish.composecodelabs.ui.theme.ComposeCodeLabsTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCodeLabsTheme {
                MySootheApp()
            }
        }
    }
}
val alignYourBodyData = listOf(
    RowItem(drawable = R.drawable.ab1_inversions, text = R.string.ab1_inversions),
    RowItem(drawable = R.drawable.ab2_quick_yoga, text = R.string.ab2_quick_yoga),
    RowItem(drawable = R.drawable.ab3_stretching, text = R.string.ab3_stretching),
    RowItem(drawable = R.drawable.ab4_tabata, text = R.string.ab4_tabata),
    RowItem(drawable = R.drawable.ab5_hiit, text = R.string.ab5_hiit),
    RowItem(drawable = R.drawable.ab6_pre_natal_yoga, text = R.string.ab6_pre_natal_yoga),
    RowItem(drawable = R.drawable.ab1_inversions, text = R.string.ab1_inversions),
    RowItem(drawable = R.drawable.ab2_quick_yoga, text = R.string.ab2_quick_yoga),
    RowItem(drawable = R.drawable.ab3_stretching, text = R.string.ab3_stretching),
    RowItem(drawable = R.drawable.ab4_tabata, text = R.string.ab4_tabata),
    RowItem(drawable = R.drawable.ab5_hiit, text = R.string.ab5_hiit),
    RowItem(drawable = R.drawable.ab6_pre_natal_yoga, text = R.string.ab6_pre_natal_yoga),
)

val favoriteCollectionsData = listOf(
    RowItem(drawable = R.drawable.fc1_short_mantras, text = R.string.fc1_short_mantras),
    RowItem(drawable = R.drawable.fc2_nature_meditations, text = R.string.fc2_nature_meditations),
    RowItem(drawable = R.drawable.fc3_stress_and_anxiety, text = R.string.fc3_stress_and_anxiety),
    RowItem(drawable = R.drawable.fc4_self_massage, text = R.string.fc4_self_massage),
    RowItem(drawable = R.drawable.fc5_overwhelmed, text = R.string.fc5_overwhelmed),
    RowItem(drawable = R.drawable.fc6_nightly_wind_down, text = R.string.fc6_nightly_wind_down),
    RowItem(drawable = R.drawable.fc1_short_mantras, text = R.string.fc1_short_mantras),
    RowItem(drawable = R.drawable.fc2_nature_meditations, text = R.string.fc2_nature_meditations),
    RowItem(drawable = R.drawable.fc3_stress_and_anxiety, text = R.string.fc3_stress_and_anxiety),
    RowItem(drawable = R.drawable.fc4_self_massage, text = R.string.fc4_self_massage),
    RowItem(drawable = R.drawable.fc5_overwhelmed, text = R.string.fc5_overwhelmed),
    RowItem(drawable = R.drawable.fc6_nightly_wind_down, text = R.string.fc6_nightly_wind_down),
)

data class RowItem(
    val drawable: Int,
    val text: Int
)

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySootheApp() {
    ComposeCodeLabsTheme {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_home))
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_profile))
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(120.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(
                drawable = item.drawable,
                text = item.text,
                modifier = Modifier.height(56.dp)
            )
        }
    }
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(item.drawable, item.text)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(id = R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp,
                bottom = 8.dp
            )
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2, heightDp = 360)
@Composable
fun ScreenContentPreview() {
    ComposeCodeLabsTheme { HomeScreen() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun SearchBarPreview() {
    ComposeCodeLabsTheme {
        SearchBar(
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyElementPreview() {
    ComposeCodeLabsTheme {
        AlignYourBodyElement(
            text = R.string.ab1_inversions,
            drawable = R.drawable.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyRowPreview() {
    ComposeCodeLabsTheme {
        AlignYourBodyRow(
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionCardPreview() {
    ComposeCodeLabsTheme {
        FavoriteCollectionCard(
            text = R.string.fc2_nature_meditations,
            drawable = R.drawable.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionsGridPreview() {
    ComposeCodeLabsTheme {
        FavoriteCollectionsGrid(
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeSectionPreview() {
    ComposeCodeLabsTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}
