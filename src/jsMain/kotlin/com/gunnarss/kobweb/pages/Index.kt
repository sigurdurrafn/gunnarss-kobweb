package com.gunnarss.kobweb.pages

import androidx.compose.runtime.Composable
import com.gunnarss.kobweb.components.layouts.PageLayout
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.Link
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText

@Page
@Composable
fun HomePage() {
    PageLayout("Siggi Gunnarss") {
        H1(Modifier.padding(16.px).toAttrs()) { SpanText("Siggi Gunnarss") }
        Spacer()
        Row(Modifier.padding(16.px)) {
            SpanText("made with ")
            Link("https://github.com/varabyte/kobweb", "Kobweb")
        }
    }
}