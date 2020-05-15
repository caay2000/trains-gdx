package com.github.caay2000.trains

class DelayedCounter(private val waitFor: Float) {

    private var elpased: Float = 0f

    fun update(delta: Float): Float {
        this.elpased += delta
        if (this.elpased > waitFor) {
            this.elpased -= this.elpased.toInt()
            return this.elpased.also {
                this.elpased = 0f
            }
        }
        return 0f;
    }

    //TODO
}