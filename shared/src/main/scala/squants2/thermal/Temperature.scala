package squants2.thermal

import squants2._

import scala.math.Numeric.Implicits.infixNumericOps

final case class Temperature[A: Numeric : Converter] private [thermal]  (value: A, unit: TemperatureUnit) extends Quantity[A, Temperature.type] {
  override type Q[B] = Temperature[B]
}

object Temperature extends BaseDimension("Temperature", "Θ") {

  override def primaryUnit: UnitOfMeasure[this.type] with PrimaryUnit = Kelvin
  override def siUnit: UnitOfMeasure[this.type] with SiBaseUnit = Kelvin
  override lazy val units: Set[UnitOfMeasure[this.type]] = Set(Kelvin, Rankine, Celsius, Fahrenheit)

  // Constructors from Numeric values
  implicit class TemperatureCons[A: Numeric : Converter](a: A) {
    def kelvin: Temperature[A] = Kelvin(a)
    def rankine: Temperature[A] = Rankine(a)
    def celsius: Temperature[A] = Celsius(a)
    def fahrenheit: Temperature[A] = Fahrenheit(a)
  }

  // Constants
  lazy val absoluteZero: Temperature[Int] = Kelvin(0)

}

abstract class TemperatureUnit(val symbol: String, val conversionFactor: ConversionFactor, val zeroOffset: Double) extends UnitOfMeasure[Temperature.type] {
  override def dimension: Temperature.type = Temperature

  override def apply[A: Numeric : Converter](value: A): Temperature[A] = Temperature(value, this)

  override def convertTo[A](quantity: Quantity[A, Temperature.type], uom: UnitOfMeasure[Temperature.type])(implicit num: Numeric[A], c: Converter[A]): Quantity[A, Temperature.type] = {
    (quantity.unit, uom) match {
      case (Kelvin, Kelvin)         => quantity
      case (Rankine, Rankine)       => quantity
      case (Celsius, Celsius)       => quantity
      case (Fahrenheit, Fahrenheit) => quantity

      case (Kelvin, Rankine)        => Rankine(quantity.value * nineFifths)
      case (Rankine, Kelvin)        => Kelvin(quantity.value * fiveNinths)

      case (Kelvin, Celsius)        => Celsius(quantity.value - celsOffset(c))
      case (Celsius, Kelvin)        => Kelvin(quantity.value + celsOffset(c))

      case (Kelvin, Fahrenheit)     => Fahrenheit(quantity.value * nineFifths + num.fromInt(32))
      case (Fahrenheit, Kelvin)     => Celsius((quantity.value - num.fromInt(32)) * fiveNinths)

      case (Rankine, Fahrenheit)    => Fahrenheit(quantity.value - fahrOffset(c))
      case (Fahrenheit, Rankine)    => Rankine(quantity.value + fahrOffset(c))

      case (Rankine, Celsius)       => Celsius((quantity.value - (fahrOffset(c) + num.fromInt(32))) * fiveNinths)
      case (Celsius, Rankine)       => Rankine((quantity.value + celsOffset(c)) * nineFifths)

      case (Celsius, Fahrenheit)    => Fahrenheit(quantity.value * nineFifths + num.fromInt(32))
      case (Fahrenheit, Celsius)    => Celsius((quantity.value - num.fromInt(32)) * fiveNinths)

    }
  }

  private def fiveNinths[A](implicit num: Numeric[A]): A = num match {
    case fnum: Fractional[A] => fnum.div(fnum.fromInt(5), fnum.fromInt(9))
    case _ => throw new UnsupportedOperationException("Unknown Numeric Type")
  }
  private def nineFifths[A](implicit num: Numeric[A]): A = num match {
    case fnum: Fractional[A] => fnum.div(fnum.fromInt(9), fnum.fromInt(5))
    case _ => throw new UnsupportedOperationException("Unknown Numeric Type")
  }
  private def celsOffset[A](implicit c: Converter[A]): A = c(Celsius.zeroOffset)
  private def fahrOffset[A](implicit c: Converter[A]): A = c(Fahrenheit.zeroOffset)

}

case object Kelvin extends TemperatureUnit("K", 1, 0) with PrimaryUnit with SiBaseUnit
case object Rankine extends TemperatureUnit("°R", 5 / 9, 0)
case object Celsius extends TemperatureUnit("°C", 1, 273.15)
case object Fahrenheit extends TemperatureUnit("°F", 5 / 9, 459.67)
