import classes._ 
import java.io.File

import scala.io.StdIn.readLine

object Queries {
  def query(input: String, countries: List[Country], airports: List[Airport], runways: List[Runway]): Unit = {
    println(s"Recherche pour : '$input'")

    val exactMatches = countries.filter { country =>
      country.name.equalsIgnoreCase(input.trim) || country.code.equalsIgnoreCase(input.trim)
    }

    val prefixMatches = if (exactMatches.nonEmpty) {
      exactMatches
    } else {
      countries.filter { country =>
        country.name.toLowerCase.startsWith(input.toLowerCase.trim) || 
        country.code.toLowerCase.startsWith(input.toLowerCase.trim)
      }
    }

    if (prefixMatches.isEmpty) {
      println(s"Aucun pays trouvé correspondant à '$input'.")
    } else {
      println("Pays correspondants :")
      prefixMatches.foreach(country => println(s"- ${country.name} (${country.code})"))

      if (prefixMatches.size > 1) {
        println("Plusieurs pays correspondent à votre recherche.")
      }

      prefixMatches.foreach { country =>
        println(s"Aéroports situés en ${country.name} :")
        val countryAirports = airports.filter(airport => {
            airport.isoCountry == country.code
        })
        if (countryAirports.isEmpty) {
          println(s"Aucun aéroport trouvé dans ${country.name}.")
        } else {
          countryAirports.foreach { airport =>
            println(s"- ${airport.name}")
            val airportRunways = runways.filter(runway => runway.airportRef == airport.id)
            if (airportRunways.isEmpty) {
              println("Aucune piste trouvée.")
            } else {
              println("Pistes :")
              airportRunways.foreach(arw => println(s"    - ${arw.surface}"))
            }
          }
        }
      }
    }
  }
}
