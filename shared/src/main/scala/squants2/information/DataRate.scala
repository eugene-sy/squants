/*                                                                      *\
** Squants                                                              **
**                                                                      **
** Scala Quantities and Units of Measure Library and DSL                **
** (c) 2013-2022, Gary Keorkunian, et al                                **
**                                                                      **
\*                                                                      */

package squants2.information

import squants2._
import squants2.time._

final case class DataRate[A: Numeric] private[squants2] (value: A, unit: DataRateUnit)
  extends Quantity[A, DataRate] with TimeDerivative[A, Information] {

  override protected[squants2] def timeIntegrated: Information[A] = Bytes(num.one)
  override protected[squants2] def derivativeTime: Time[A] = Seconds(num.one)

  // BEGIN CUSTOM OPS

  //  def /[B](that: Quantity[B])(implicit f: B => A): Frequency[A] = ???
  // END CUSTOM OPS

  def toBitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](BitsPerSecond)
  def toBytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](BytesPerSecond)
  def toKilobitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](KilobitsPerSecond)
  def toKibibitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](KibibitsPerSecond)
  def toKilobytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](KilobytesPerSecond)
  def toKibibytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](KibibytesPerSecond)
  def toMegabitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](MegabitsPerSecond)
  def toMebibitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](MebibitsPerSecond)
  def toMegabytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](MegabytesPerSecond)
  def toMebibytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](MebibytesPerSecond)
  def toGigabitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](GigabitsPerSecond)
  def toGibibitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](GibibitsPerSecond)
  def toGigabytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](GigabytesPerSecond)
  def toGibibytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](GibibytesPerSecond)
  def toTerabitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](TerabitsPerSecond)
  def toTebibitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](TebibitsPerSecond)
  def toTerabytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](TerabytesPerSecond)
  def toTebibytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](TebibytesPerSecond)
  def toPetabitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](PetabitsPerSecond)
  def toPebibitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](PebibitsPerSecond)
  def toPetabytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](PetabytesPerSecond)
  def toPebibytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](PebibytesPerSecond)
  def toExabitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](ExabitsPerSecond)
  def toExbibitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](ExbibitsPerSecond)
  def toExabytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](ExabytesPerSecond)
  def toExbibytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](ExbibytesPerSecond)
  def toZettabitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](ZettabitsPerSecond)
  def toZebibitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](ZebibitsPerSecond)
  def toZettabytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](ZettabytesPerSecond)
  def toZebibytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](ZebibytesPerSecond)
  def toYottabitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](YottabitsPerSecond)
  def toYobibitsPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](YobibitsPerSecond)
  def toYottabytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](YottabytesPerSecond)
  def toYobibytesPerSecond[B: Numeric](implicit f: A => B): B = toNum[B](YobibytesPerSecond)
}

object DataRate extends Dimension[DataRate]("Data Rate") {

  override def primaryUnit: UnitOfMeasure[DataRate] with PrimaryUnit[DataRate] = BytesPerSecond
  override def siUnit: UnitOfMeasure[DataRate] with SiUnit[DataRate] = BytesPerSecond
  override lazy val units: Set[UnitOfMeasure[DataRate]] = 
    Set(BitsPerSecond, BytesPerSecond, KilobitsPerSecond, KibibitsPerSecond, KilobytesPerSecond, KibibytesPerSecond, MegabitsPerSecond, MebibitsPerSecond, MegabytesPerSecond, MebibytesPerSecond, GigabitsPerSecond, GibibitsPerSecond, GigabytesPerSecond, GibibytesPerSecond, TerabitsPerSecond, TebibitsPerSecond, TerabytesPerSecond, TebibytesPerSecond, PetabitsPerSecond, PebibitsPerSecond, PetabytesPerSecond, PebibytesPerSecond, ExabitsPerSecond, ExbibitsPerSecond, ExabytesPerSecond, ExbibytesPerSecond, ZettabitsPerSecond, ZebibitsPerSecond, ZettabytesPerSecond, ZebibytesPerSecond, YottabitsPerSecond, YobibitsPerSecond, YottabytesPerSecond, YobibytesPerSecond)

  implicit class DataRateCons[A](a: A)(implicit num: Numeric[A]) {
    def bitsPerSecond: DataRate[A] = BitsPerSecond(a)
    def bytesPerSecond: DataRate[A] = BytesPerSecond(a)
    def kilobitsPerSecond: DataRate[A] = KilobitsPerSecond(a)
    def kibibitsPerSecond: DataRate[A] = KibibitsPerSecond(a)
    def kilobytesPerSecond: DataRate[A] = KilobytesPerSecond(a)
    def kibibytesPerSecond: DataRate[A] = KibibytesPerSecond(a)
    def megabitsPerSecond: DataRate[A] = MegabitsPerSecond(a)
    def mebibitsPerSecond: DataRate[A] = MebibitsPerSecond(a)
    def megabytesPerSecond: DataRate[A] = MegabytesPerSecond(a)
    def mebibytesPerSecond: DataRate[A] = MebibytesPerSecond(a)
    def gigabitsPerSecond: DataRate[A] = GigabitsPerSecond(a)
    def gibibitsPerSecond: DataRate[A] = GibibitsPerSecond(a)
    def gigabytesPerSecond: DataRate[A] = GigabytesPerSecond(a)
    def gibibytesPerSecond: DataRate[A] = GibibytesPerSecond(a)
    def terabitsPerSecond: DataRate[A] = TerabitsPerSecond(a)
    def tebibitsPerSecond: DataRate[A] = TebibitsPerSecond(a)
    def terabytesPerSecond: DataRate[A] = TerabytesPerSecond(a)
    def tebibytesPerSecond: DataRate[A] = TebibytesPerSecond(a)
    def petabitsPerSecond: DataRate[A] = PetabitsPerSecond(a)
    def pebibitsPerSecond: DataRate[A] = PebibitsPerSecond(a)
    def petabytesPerSecond: DataRate[A] = PetabytesPerSecond(a)
    def pebibytesPerSecond: DataRate[A] = PebibytesPerSecond(a)
    def exabitsPerSecond: DataRate[A] = ExabitsPerSecond(a)
    def exbibitsPerSecond: DataRate[A] = ExbibitsPerSecond(a)
    def exabytesPerSecond: DataRate[A] = ExabytesPerSecond(a)
    def exbibytesPerSecond: DataRate[A] = ExbibytesPerSecond(a)
    def zettabitsPerSecond: DataRate[A] = ZettabitsPerSecond(a)
    def zebibitsPerSecond: DataRate[A] = ZebibitsPerSecond(a)
    def zettabytesPerSecond: DataRate[A] = ZettabytesPerSecond(a)
    def zebibytesPerSecond: DataRate[A] = ZebibytesPerSecond(a)
    def yottabitsPerSecond: DataRate[A] = YottabitsPerSecond(a)
    def yobibitsPerSecond: DataRate[A] = YobibitsPerSecond(a)
    def yottabytesPerSecond: DataRate[A] = YottabytesPerSecond(a)
    def yobibytesPerSecond: DataRate[A] = YobibytesPerSecond(a)
  }

  lazy val bitPerSecond: DataRate[Int] = BitsPerSecond(1)
  lazy val bytePerSecond: DataRate[Int] = BytesPerSecond(1)
  lazy val kilobitPerSecond: DataRate[Int] = KilobitsPerSecond(1)
  lazy val kibibitPerSecond: DataRate[Int] = KibibitsPerSecond(1)
  lazy val kilobytePerSecond: DataRate[Int] = KilobytesPerSecond(1)
  lazy val kibibytePerSecond: DataRate[Int] = KibibytesPerSecond(1)
  lazy val megabitPerSecond: DataRate[Int] = MegabitsPerSecond(1)
  lazy val mebibitPerSecond: DataRate[Int] = MebibitsPerSecond(1)
  lazy val megabytePerSecond: DataRate[Int] = MegabytesPerSecond(1)
  lazy val mebibytePerSecond: DataRate[Int] = MebibytesPerSecond(1)
  lazy val gigabitPerSecond: DataRate[Int] = GigabitsPerSecond(1)
  lazy val gibibitPerSecond: DataRate[Int] = GibibitsPerSecond(1)
  lazy val gigabytePerSecond: DataRate[Int] = GigabytesPerSecond(1)
  lazy val gibibytePerSecond: DataRate[Int] = GibibytesPerSecond(1)
  lazy val terabitPerSecond: DataRate[Int] = TerabitsPerSecond(1)
  lazy val tebibitPerSecond: DataRate[Int] = TebibitsPerSecond(1)
  lazy val terabytePerSecond: DataRate[Int] = TerabytesPerSecond(1)
  lazy val tebibytePerSecond: DataRate[Int] = TebibytesPerSecond(1)
  lazy val petabitPerSecond: DataRate[Int] = PetabitsPerSecond(1)
  lazy val pebibitPerSecond: DataRate[Int] = PebibitsPerSecond(1)
  lazy val petabytePerSecond: DataRate[Int] = PetabytesPerSecond(1)
  lazy val pebibytePerSecond: DataRate[Int] = PebibytesPerSecond(1)
  lazy val exabitPerSecond: DataRate[Int] = ExabitsPerSecond(1)
  lazy val exbibitPerSecond: DataRate[Int] = ExbibitsPerSecond(1)
  lazy val exabytePerSecond: DataRate[Int] = ExabytesPerSecond(1)
  lazy val exbibytePerSecond: DataRate[Int] = ExbibytesPerSecond(1)
  lazy val zettabitPerSecond: DataRate[Int] = ZettabitsPerSecond(1)
  lazy val zebibitPerSecond: DataRate[Int] = ZebibitsPerSecond(1)
  lazy val zettabytePerSecond: DataRate[Int] = ZettabytesPerSecond(1)
  lazy val zebibytePerSecond: DataRate[Int] = ZebibytesPerSecond(1)
  lazy val yottabitPerSecond: DataRate[Int] = YottabitsPerSecond(1)
  lazy val yobibitPerSecond: DataRate[Int] = YobibitsPerSecond(1)
  lazy val yottabytePerSecond: DataRate[Int] = YottabytesPerSecond(1)
  lazy val yobibytePerSecond: DataRate[Int] = YobibytesPerSecond(1)

}

abstract class DataRateUnit(val symbol: String, val conversionFactor: ConversionFactor) extends UnitOfMeasure[DataRate] {
  override def dimension: Dimension[DataRate] = DataRate
  override def apply[A: Numeric](value: A): DataRate[A] = DataRate(value, this)
}

case object BitsPerSecond extends DataRateUnit("bps", 0.125)
case object BytesPerSecond extends DataRateUnit("B/s", 1) with PrimaryUnit[DataRate] with SiUnit[DataRate]
case object KilobitsPerSecond extends DataRateUnit("Kbps", 125)
case object KibibitsPerSecond extends DataRateUnit("Kibps", 128)
case object KilobytesPerSecond extends DataRateUnit("KB/s", MetricSystem.Kilo)
case object KibibytesPerSecond extends DataRateUnit("KiB/s", BinarySystem.Kilo)
case object MegabitsPerSecond extends DataRateUnit("Mbps", 125000)
case object MebibitsPerSecond extends DataRateUnit("Mibps", 131072)
case object MegabytesPerSecond extends DataRateUnit("MB/s", MetricSystem.Mega)
case object MebibytesPerSecond extends DataRateUnit("MiB/s", BinarySystem.Mega)
case object GigabitsPerSecond extends DataRateUnit("Gbps", 125000000)
case object GibibitsPerSecond extends DataRateUnit("Gibps", 134217728)
case object GigabytesPerSecond extends DataRateUnit("GB/s", MetricSystem.Giga)
case object GibibytesPerSecond extends DataRateUnit("GiB/s", BinarySystem.Giga)
case object TerabitsPerSecond extends DataRateUnit("Tbps", 1.25E11)
case object TebibitsPerSecond extends DataRateUnit("Tibps", 1.37438953472E11)
case object TerabytesPerSecond extends DataRateUnit("TB/s", MetricSystem.Tera)
case object TebibytesPerSecond extends DataRateUnit("TiB/s", BinarySystem.Tera)
case object PetabitsPerSecond extends DataRateUnit("Pbps", 1.25E14)
case object PebibitsPerSecond extends DataRateUnit("Pibps", 1.40737488355328E14)
case object PetabytesPerSecond extends DataRateUnit("PB/s", MetricSystem.Peta)
case object PebibytesPerSecond extends DataRateUnit("PiB/s", BinarySystem.Peta)
case object ExabitsPerSecond extends DataRateUnit("Ebps", 1.25E17)
case object ExbibitsPerSecond extends DataRateUnit("Eibps", 1.44115188075855872E17)
case object ExabytesPerSecond extends DataRateUnit("EB/s", MetricSystem.Exa)
case object ExbibytesPerSecond extends DataRateUnit("EiB/s", BinarySystem.Exa)
case object ZettabitsPerSecond extends DataRateUnit("Zbps", 1.25E20)
case object ZebibitsPerSecond extends DataRateUnit("Zibps", 1.4757395258967641E20)
case object ZettabytesPerSecond extends DataRateUnit("ZB/s", MetricSystem.Zetta)
case object ZebibytesPerSecond extends DataRateUnit("ZiB/s", BinarySystem.Zetta)
case object YottabitsPerSecond extends DataRateUnit("Ybps", 1.25E23)
case object YobibitsPerSecond extends DataRateUnit("Yibps", 1.5111572745182865E23)
case object YottabytesPerSecond extends DataRateUnit("YB/s", MetricSystem.Yotta)
case object YobibytesPerSecond extends DataRateUnit("YiB/s", BinarySystem.Yotta)
