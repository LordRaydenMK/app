package dev.sanastasov.app.weight.domain

@JvmInline
value class Weight(val value: Int) {
    init {
        require(value > 0) { "Weight must be positive, found '$value'" }
        require(value < 1000) { "Max allow weight is 1000 found '$value'" }
    }
}
