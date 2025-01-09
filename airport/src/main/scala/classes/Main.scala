import classes._
import java.io.File
import scala.io.StdIn.readLine

object Main extends App {
  val countriesFilePath = "./countries.csv"
  val airportsFilePath = "./airports.csv"
  val runwaysFilePath = "./runways.csv"

  val countries = CsvParser.parseCountries(countriesFilePath)
  val airports = CsvParser.parseAirports(airportsFilePath)
  val runways = CsvParser.parseRunways(runwaysFilePath)

  println(s"Nombre de pays : ${countries.size}")
  println(s"Nombre d'aéroports : ${airports.size}")
  println(s"Nombre de pistes : ${runways.size}")

  def displayCountries(countries: List[Country]): Unit = {
    println("=== Liste des Pays ===")
    countries.take(10).foreach { country =>
      println(f"- ID: ${country.id}%-6s Code: ${country.code}%-3s Nom: ${country.name}%-30s Continent: ${country.continent}%-3s Wikipedia: ${country.wikipediaLink}%-40s Keywords: ${country.keywords}")
    }
  }

  def displayAirports(airports: List[Airport]): Unit = {
    println("=== Liste des Aéroports ===")
    airports.take(10).foreach { airport =>
      println(f"- ID: ${airport.id}%-6s Ident: ${airport.ident}%-8s Nom: ${airport.name}%-40s Iso: ${airport.isoCountry}%-40s")
    }
  }

  def displayRunways(runways: List[Runway]): Unit = {
    println("=== Liste des Pistes ===")
    runways.take(10).foreach { runway =>
      println(f"- ID: ${runway.id}%-6s AirportRef: ${runway.airportRef}%-6s Surface: ${runway.surface}%-15s Ident: ${runway.leIdent}%-6s")
    }
  }

  displayCountries(countries)
  displayAirports(airports)
  displayRunways(runways)

  def displayMenu(): Unit = {
    println("Choisissez une option")
    println("1 - Requete : chercher les différentes informations par pays")
    println("2 - Rapport (statistique, classement)")
    println("3 - Quitter le programme")
  }

  def menuLoop(): Unit = {
    displayMenu()
    val choice = readLine().trim

    choice match {
      case "1" =>
        println("Entrez un nom de pays ou un code (ex : france ou FR)")
        val input = readLine().trim
        Queries.query(input, countries, airports, runways)
        menuLoop()
      case "2" =>
        Reports.displayMenu(countries, airports, runways)
        menuLoop()
      case "3" =>
        println("Merci pour votre participation, au revoir")
      case _ =>
        println("Choix invalide, veuillez réessayer !")
        menuLoop() 
    }
  }

  menuLoop()
}
