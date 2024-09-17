package com.example.lr_1

class Calculator(private val fuel: FuelComponents?, private val lowerHeatingsComponents: LowerHeatingComponents?) {
    fun calculateFuelParametrs(): Map<String, Any> {
        if(fuel != null) {
            val kRS = 100 / (100 - fuel.moisture)
            val kRG = 100 / (100 - fuel.moisture - fuel.ash)

            val hydrogenDry = fuel.hydrogen * kRS
            val carbonDry = fuel.carbon * kRS
            val sulfurDry = fuel.sulfur * kRS
            val nitrogenDry = fuel.nitrogen * kRS
            val oxygenDry = fuel.oxygen * kRS
            val ashDry = fuel.ash * kRS

            val hydrogenCombustible = fuel.hydrogen * kRG
            val carbonCombustible = fuel.carbon * kRG
            val sulfurCombustible = fuel.sulfur * kRG
            val nitrogenCombustible = fuel.nitrogen * kRG
            val oxygenCombustible = fuel.oxygen * kRG

            val qPN = 339 * fuel.carbon + 1030 * fuel.hydrogen - 108.8 * (fuel.oxygen - fuel.sulfur) - 25 * fuel.moisture

            return mapOf (
                "kRS" to kRS,
                "kRG" to kRG,
                "hydrogenDry" to "$hydrogenDry %",
                "carbonDry" to "$carbonDry %",
                "sulfurDry" to "$sulfurDry %",
                "nitrogenDry" to nitrogenDry,
                "oxygenDry" to "$oxygenDry %",
                "ashDry" to "$ashDry %",
                "hydrogenCombustible" to "$hydrogenCombustible %",
                "carbonCombustible" to "$carbonCombustible %",
                "sulfurCombustible" to "$sulfurCombustible %",
                "nitrogenCombustible" to nitrogenCombustible,
                "oxygenCombustible" to "$oxygenCombustible %",
                "lowerHeatingValue" to "$qPN МДж/кг"
            )
        } else return mapOf("error" to "Fuel components are missing")

    }

    fun calculateLowerHeatingValue(): Float? {
        if(lowerHeatingsComponents != null) {
            val moistureFraction = lowerHeatingsComponents.moisture / 100
            val ashFraction = lowerHeatingsComponents.ash / 100

            val heatingValueDry =
                lowerHeatingsComponents.lowerHeatingValue * (1 - moistureFraction) * (1 - ashFraction)

            return heatingValueDry
        } else return null
    }
}