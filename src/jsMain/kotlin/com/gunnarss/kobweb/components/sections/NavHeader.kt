package com.gunnarss.kobweb.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.dom.ElementRefScope
import com.varabyte.kobweb.compose.dom.ref
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.fa.FaMoon
import com.varabyte.kobweb.silk.components.icons.fa.FaSun
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.theme.SilkTheme
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.rememberColorMode
import com.varabyte.kobweb.silk.theme.shapes.Circle
import com.varabyte.kobweb.silk.theme.shapes.clip
import org.jetbrains.compose.web.css.px
import org.w3c.dom.HTMLElement

private val margin = Modifier.margin(0.px, 15.px)

@Composable
private fun NavLink(path: String, text: String, ref: ElementRefScope<HTMLElement>? = null) {
    Link(
        path,
        text,
        // Intentionally invert the header colors
        margin.color(SilkTheme.palette.background),
        UndecoratedLinkVariant,
        ref = ref
    )
}

@Composable
fun NavHeader() {
    var colorMode by rememberColorMode()
    val palette = SilkTheme.palette
    Box(
        Modifier
            .fillMaxWidth()
            .height(50.px)
            // Intentionally invert the header colors
            .backgroundColor(palette.color),
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavLink("/", "Home")
            NavLink("https://github.com/sigurdurrafn/", "Code")
            NavLink("https://www.twitter.com/sigurdur", "Twitter")
            NavLink("https://www.linkedin.com/in/sigurdurrafn/", "LinkedIn")
            NavLink("https://www.festinasweden.se/", "Join me!")
            NavLink("/blog", "Blog")
            NavLink(
                path = "https://mastodon.online/@sigurdur",
                text = "Mastodon",
                ref = ref { it.setAttribute("rel", "me") }
            )
            Spacer()
            Button(
                onClick = { colorMode = colorMode.opposite() },
                margin.clip(Circle())
            ) {
                Box(Modifier.margin(6.px)) {
                    when (colorMode) {
                        ColorMode.LIGHT -> FaSun()
                        ColorMode.DARK -> FaMoon()
                    }
                }
            }
        }
    }
}