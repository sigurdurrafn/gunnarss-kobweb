package com.gunnarss.kobweb.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.navigation.Link
import com.gunnarss.kobweb.components.layouts.PageLayout
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.dom.P

@Page
@Composable
fun AboutPage() {
    PageLayout("ABOUT") {
        SpanText("This is a very basic site made using Kobweb")
        P()
        Link("/", "Go Home")
    }
}