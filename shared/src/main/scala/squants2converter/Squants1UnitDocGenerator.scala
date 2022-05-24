package squants2converter

import squants._
import squants.electro._
import squants.energy._
import squants.information._
import squants.market._
import squants.mass._
import squants.motion._
import squants.photo._
import squants.radio._
import squants.space._
import squants.thermal._
import squants.time._

import java.io.{ FileWriter, PrintWriter }

object Squants1UnitDocGenerator extends App {

  val allDimensions = Set[Dimension[_]](
    ElectricCurrent, Mass, ChemicalAmount, LuminousIntensity, Length, Temperature, Time,

    AreaElectricChargeDensity, Capacitance, Conductivity, ElectricalConductance, ElectricalResistance,
    ElectricCharge, ElectricChargeDensity, ElectricChargeMassRatio, ElectricCurrentDensity,
    ElectricFieldStrength, ElectricPotential, Inductance, LinearElectricChargeDensity,
    MagneticFlux, MagneticFluxDensity, Permeability, Permittivity, Resistivity,

    Energy, EnergyDensity, MolarEnergy, Power, PowerDensity, PowerRamp, SpecificEnergy,

    DataRate, Information,

    AreaDensity, Density, MomentOfInertia,

    Acceleration, AngularAcceleration, AngularVelocity, Force, Jerk,
    MassFlow, Momentum, Pressure, PressureChange, Torque, VolumeFlow, Yank,

    Illuminance, Luminance, LuminousEnergy, LuminousExposure, LuminousFlux,

    Activity, AreaTime, Dose, Irradiance, ParticleFlux, Radiance, RadiantIntensity,
    SpectralIntensity, SpectralIrradiance, SpectralPower,

    Angle, Area, SolidAngle, Volume,

    ThermalCapacity,

    Frequency,

    Dimensionless
  )

  val file = new FileWriter("UNITS.md")
  val printer = new PrintWriter(file)
  printAllDimensions(printer)
  printMarket(printer)
  file.close()

  def printAllDimensions(printer: PrintWriter): Unit = {

    printer.println("# Squants - Supported Dimensions and Units")

    printer.println("## Index")
    printer.println("")
    printer.println("|Package|Dimensions|")
    printer.println("|----------------------------|-----------------------------------------------------------|")
    allDimensions.groupBy(d => d.getClass.getPackage.getName)
      .toList.sortBy(_._1)
      .foreach {
        case (p, ds) =>
          val dims = ds.toList.sortBy(_.name)
          printer.println(s"|$p|${dims.map(d => s"[${d.name}](#${d.name.toLowerCase})").mkString(", ")}|")
      }
    printer.println("|squants.market|[Money](#money)|")

    printer.println(s"#### Dimension Count: ${allDimensions.size + 1}")  // Add one for Money
    printer.println(s"#### Unit Count: ${allDimensions.map(_.units.size).sum}")
    printer.println(s"#### Currency Count: ${defaultCurrencySet.size}")

    allDimensions.toList
      .sortBy { d => d.name }
      .sortBy { d => !d.isInstanceOf[BaseDimension] }.foreach { d =>
        writeDimension(printer, d)
      }

    printer.flush()
  }

  private def writeDimension(printer: PrintWriter, d: Dimension[_]): Unit = {
    printer.println("")
    d match {
      case bd: BaseDimension =>
        printer.println(s"## ${d.name.replace(" ", "")}")
        printer.println(s"##### Dimensional Symbol:  ${bd.dimensionSymbol}")
        printer.println(s"#### Primary Unit: ${d.primaryUnit.getClass.getSimpleName.replace("$", "")} (1 ${d.primaryUnit.symbol})")
        printer.println(s"#### SI Base Unit: ${d.siUnit.getClass.getSimpleName.replace("$", "")} (1 ${d.siUnit.symbol})")

      case _ =>
        printer.println(s"## ${d.name}")
        printer.println(s"#### Primary Unit: ${d.primaryUnit.getClass.getSimpleName.replace("$", "")} (1 ${d.primaryUnit.symbol})")
        printer.println(s"#### SI Unit: ${d.siUnit.getClass.getSimpleName.replace("$", "")} (1 ${d.siUnit.symbol})")
    }
    printer.println("|Unit|Conversion Factor|")
    printer.println("|----------------------------|-----------------------------------------------------------|")
    d.units
      .filterNot { (u: UnitOfMeasure[_]) => u eq d.primaryUnit }.toList
      .sortBy { (u: UnitOfMeasure[_]) => u.convertFrom(1) }
      .foreach { (u: UnitOfMeasure[_]) =>
        val cf = s"1 ${u.symbol} = ${u.convertFrom(1)} ${d.primaryUnit.symbol}"
        printer.println(s"|${u.getClass.getSimpleName.replace("$", "")}| $cf|")
      }
    printer.println("")
    printer.println(s"[Go to Code](shared/src/main/scala/${d.getClass.getPackage.getName.replace(".", "/")}/${d.getClass.getSimpleName.replace("$", "")}.scala)")

  }

  private def printMarket(printer: PrintWriter): Unit = {
    printer.println("")
    val mc = squants.market.defaultCurrencySet
    val d = Money
    printer.println(s"## ${d.name}")
    printer.println("|Currency|ISO Code|Symbol|Precision|Default Format|")
    printer.println("|---------------------|-------|-----------------------|--------------------|----------------|")
    mc.toList.sortBy { _.name }
      .foreach { cur =>
        printer.println(s"|${cur.name}| ${cur.code}| ${cur.symbol}|${cur.formatDecimals}|${cur(1).toFormattedString}|")
      }
    printer.println("")
    printer.println(s"[Go to Code](shared/src/main/scala/squants/market/Money.scala)")

  }

}
