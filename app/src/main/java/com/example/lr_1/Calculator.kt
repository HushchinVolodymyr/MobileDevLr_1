package com.example.lr_1

class Calculator() {
    fun calculateFuelParametrs(fuel: FuelComponents?): Map<String, Any> {
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

            val lowerHeatingValueWorking = (339 * fuel.carbon + 1030 * fuel.hydrogen - 108.8 * (fuel.oxygen - fuel.sulfur) - 25 * fuel.moisture) / 1000

            val lowerHeatingValueDry = (lowerHeatingValueWorking + 0.025 * fuel.moisture) * (100 / (100 - fuel.moisture))

            val lowerHeatingValueCombustible = (lowerHeatingValueWorking + 0.025 * fuel.moisture) * (100 / (100 - fuel.moisture - fuel.ash))

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
                "lowerHeatingValueWorking" to "$lowerHeatingValueWorking МДж/кг",
                "lowerHeatingValueDry" to "$lowerHeatingValueDry МДж/кг",
                "lowerHeatingValueCombustible" to "$lowerHeatingValueCombustible МДж/кг"
            )
        } else return mapOf("error" to "Fuel components are missing")

    }

    fun wokingCalc(element: Float, lowerHeatingsComponents: LowerHeatingComponents): Float {
        return element * (100 - lowerHeatingsComponents.moisture - lowerHeatingsComponents.ash) / 100
    }

    fun calculateLowerHeatingValue(lowerHeatingsComponents: LowerHeatingComponents?): Map<String, Any> {
        if (lowerHeatingsComponents != null) {
            val moistureFraction = lowerHeatingsComponents.moisture / 100
            val ashFraction = lowerHeatingsComponents.ash / 100

            val carbonWorking = this.wokingCalc(lowerHeatingsComponents.carbon, lowerHeatingsComponents)
            val hydrogenWorking = this.wokingCalc(lowerHeatingsComponents.hydrogen, lowerHeatingsComponents)
            val oxygenWorking = this.wokingCalc(lowerHeatingsComponents.oxygen, lowerHeatingsComponents)
            val sulfurWorking = this.wokingCalc(lowerHeatingsComponents.sulfur, lowerHeatingsComponents)
            val ashWorking = this.wokingCalc(lowerHeatingsComponents.ash, lowerHeatingsComponents)
            val vanadiumWorking = this.wokingCalc(lowerHeatingsComponents.vanadium, lowerHeatingsComponents)

            val heatingValueWorkingMass = lowerHeatingsComponents.lowerHeatingValue * (1 - moistureFraction) * (1 - ashFraction)

            return mapOf(
                "carbonWorkingMass" to "${carbonWorking} %",
                "hydrogenWorkingMass" to "${hydrogenWorking} %",
                "oxygenWorkingMass" to "${oxygenWorking} %",
                "sulfurWorkingMass" to "${sulfurWorking} %",
                "ashWorkingMass" to "${ashWorking} %",
                "vanadiumWorkingMass" to "${vanadiumWorking} %",
                "lowerHeatingValueWorkingMass" to "%.2f МДж/кг".format(heatingValueWorkingMass)
            )
        } else return return mapOf("error" to "Fuel components are missing")
    }
}