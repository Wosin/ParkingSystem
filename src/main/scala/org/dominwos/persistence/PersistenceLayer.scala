import java.time.{LocalDate, LocalDateTime}

trait PersistenceLayer {
  def getAllEntries(): Seq[(String, LocalDateTime)]

  def getAllEntriesForDate(date: LocalDateTime): Seq[(String, LocalDateTime)]

  def getEntry(registrationNumber: String) : (String, LocalDateTime)
}
