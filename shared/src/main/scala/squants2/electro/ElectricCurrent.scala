/*                                                                      *\
** Squants                                                              **
**                                                                      **
** Scala Quantities and Units of Measure Library and DSL                **
** (c) 2013-2022, Gary Keorkunian, et al                                **
**                                                                      **
\*                                                                      */

package squants2.electro

import squants2._
import scala.math.Numeric.Implicits.infixNumericOps

final case class ElectricCurrent[A: Numeric] private [squants2]  (value: A, unit: ElectricCurrentUnit)
  extends Quantity[A, ElectricCurrent.type] {
  override type Q[B] = ElectricCurrent[B]

  // BEGIN CUSTOM OPS
  // END CUSTOM OPS

  def toMilliamperes: A = to(Milliamperes)
  def toAmperes: A = to(Amperes)
}

object ElectricCurrent extends Dimension("ElectricCurrent") {

  override def primaryUnit: UnitOfMeasure[this.type] with PrimaryUnit = Amperes
  override def siUnit: UnitOfMeasure[this.type] with SiUnit = Amperes
  override lazy val units: Set[UnitOfMeasure[this.type]] = 
    Set(Milliamperes, Amperes)

  implicit class ElectricCurrentCons[A](a: A)(implicit num: Numeric[A]) {
    def milliamperes: ElectricCurrent[A] = Milliamperes(a)
    def amperes: ElectricCurrent[A] = Amperes(a)
  }

  lazy val milliamperes: ElectricCurrent[Int] = Milliamperes(1)
  lazy val amperes: ElectricCurrent[Int] = Amperes(1)

  override def numeric[A: Numeric]: QuantityNumeric[A, this.type] = ElectricCurrentNumeric[A]()
  private case class ElectricCurrentNumeric[A: Numeric]() extends QuantityNumeric[A, this.type](this) {
    override def times(x: Quantity[A, ElectricCurrent.type], y: Quantity[A, ElectricCurrent.type]): Quantity[A, ElectricCurrent.this.type] =
      Amperes(x.to(Amperes) * y.to(Amperes))
  }
}

abstract class ElectricCurrentUnit(val symbol: String, val conversionFactor: ConversionFactor) extends UnitOfMeasure[ElectricCurrent.type] {
  override lazy val dimension: ElectricCurrent.type = ElectricCurrent
  override def apply[A: Numeric](value: A): ElectricCurrent[A] = ElectricCurrent(value, this)
}

case object Milliamperes extends ElectricCurrentUnit("mA", 0.001) with SiUnit
case object Amperes extends ElectricCurrentUnit("A", 1) with PrimaryUnit with SiUnit
