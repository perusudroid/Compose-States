package com.example.findmyip.sample

sealed interface SampleEvent {

    data class onNextPressed(val content : String) : SampleEvent
    object onBackPressed : SampleEvent

}