data class Archive(var name: String, val mapNotes: MutableList<Note> = mutableListOf()){} // создаём сущность Архива

data class Note (val textNote: String, val content: String) {} // сущность заметок


