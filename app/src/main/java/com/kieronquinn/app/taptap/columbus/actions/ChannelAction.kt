package com.kieronquinn.app.taptap.columbus.actions

import android.content.Context
import com.google.android.systemui.columbus.sensors.GestureSensor
import com.kieronquinn.app.taptap.v2.ui.screens.setup.configuration.SetupConfigurationViewModel
import kotlinx.coroutines.channels.Channel

class ChannelAction(context: Context, private val eventType: TapEvent): ActionBase(context, emptyList()) {

    internal val runFlow = Channel<TapEvent>()

    override fun onTrigger(detectionProperties: GestureSensor.DetectionProperties?) {
        super.onTrigger(detectionProperties)
        runFlow.offer(eventType)
    }

    enum class TapEvent {
        DOUBLE, TRIPLE
    }

}

fun ChannelAction.TapEvent.getRippleCount(): Int = when(this){
    ChannelAction.TapEvent.DOUBLE -> 2
    ChannelAction.TapEvent.TRIPLE -> 3
}